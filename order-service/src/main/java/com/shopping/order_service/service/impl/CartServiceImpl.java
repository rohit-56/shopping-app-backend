package com.shopping.order_service.service.impl;

import com.shopping.order_service.common.mapper.CartMapper;
import com.shopping.order_service.entity.Cart;
import com.shopping.order_service.exception.HandleCartItemException;
import com.shopping.order_service.external.client.ItemServiceClient;
import com.shopping.order_service.external.client.http.ItemResponse;
import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartItemResponse;
import com.shopping.order_service.http.response.CartResponse;
import com.shopping.order_service.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements ICartService {

    private StringRedisTemplate stringRedisTemplate;

    private final ItemServiceClient itemServiceClient;

    private static final String CART_PREFIX = "cart:";

    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    private HashMap<String,HashMap<Long, ItemResponse>> cartItemsMap = new HashMap<>();

    public CartServiceImpl(StringRedisTemplate stringRedisTemplate, ItemServiceClient itemServiceClient) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.itemServiceClient = itemServiceClient;
    }

    @Override
    public boolean addItemToCart(CartRequest cartRequest) {
        Cart cart = CartMapper.fromCartRequestToCartEntity.apply(cartRequest);

        String key = CART_PREFIX + cart.getUserId();
        String hashId = ""+cart.getItemId();
        log.info("Adding item to cart for user: "+cart.getUserId()+" with hashId: "+hashId);
        try {
            stringRedisTemplate.opsForHash().increment(key, hashId, cart.getQuantity());
            stringRedisTemplate.expire(key, 10, TimeUnit.MINUTES);

            ItemResponse itemResponse = itemServiceClient.getItemById(cartRequest.getItemId()).getBody();

            if(cartItemsMap.containsKey(cart.getUserId())){
                HashMap<Long,ItemResponse> map = cartItemsMap.get(cart.getUserId());
                map.put(cart.getItemId(), itemResponse);

                cartItemsMap.put(cart.getUserId(),map);
            }
            else {
                HashMap<Long,ItemResponse> map = new HashMap<>();
                map.put(cart.getItemId(), itemResponse);

                cartItemsMap.put(cart.getUserId(),map);
            }

        }catch (Exception e){
            throw new HandleCartItemException("Failed to add item to cart.");
        }
        return true;
    }

    @Override
    public boolean removeItemFromCart(CartRequest cartRequest) {
        String key = CART_PREFIX + cartRequest.getUserId();
        String hashIdKey = ""+cartRequest.getItemId();

        try{
          Long count = stringRedisTemplate.opsForHash().delete(key,hashIdKey);
          if(count>0){
              log.info("Removing item from cart for user: "+cartRequest.getUserId()+" with hashId: "+hashIdKey);
              cartItemsMap.get(cartRequest.getUserId()).remove(cartRequest.getItemId());
              return true;
          }
          else {
              throw new HandleCartItemException("Failed to remove item from cart.");
          }
    }catch (Exception e) {
            throw new HandleCartItemException("Failed to delete item to cart.");
        }
    }

    @Override
    public CartResponse getAllCartItems(String  userId) {

        String key = CART_PREFIX + userId;
        Map<Object,Object> redisMap = stringRedisTemplate.opsForHash().entries(key);


        CartResponse cartResponse = new CartResponse();
        cartResponse.setUserId(userId);

        //set dynamic cart Id
        cartResponse.setCartId(1);

        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        float amount = 0;
        for (Map.Entry<Object,Object> entry : redisMap.entrySet()) {
          long itemId = Long.parseLong(entry.getKey().toString());
          int quantity = Integer.parseInt(entry.getValue().toString());

          ItemResponse itemResponse = cartItemsMap.get(userId).get(itemId);
          log.info("Get Items for Item Id: "+itemId+" Quantity: "+quantity);
          CartItemResponse cartItemResponse = new CartItemResponse();
          cartItemResponse.setItemId(itemId);
          cartItemResponse.setQuantity(quantity);
          cartItemResponse.setItemName(itemResponse.getItemName());
          cartItemResponse.setPrice( itemResponse.getAmount());
          cartItemResponse.setTotalPrice(itemResponse.getAmount()*quantity);

          cartItemResponses.add(cartItemResponse);

          amount += cartItemResponse.totalPrice;

        }


        cartResponse.setItems(cartItemResponses);
        cartResponse.setTotalAmount(amount);


        return cartResponse;
    }
}
