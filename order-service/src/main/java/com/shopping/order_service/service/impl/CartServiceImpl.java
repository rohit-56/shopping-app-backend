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

    private HashMap<Integer,HashMap<Long, ItemResponse>> cartItems = new HashMap<>();

    public CartServiceImpl(StringRedisTemplate stringRedisTemplate, ItemServiceClient itemServiceClient) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.itemServiceClient = itemServiceClient;
    }

    @Override
    public boolean addItemToCart(CartRequest cartRequest) {
        Cart cart = CartMapper.fromCartRequestToCartEntity.apply(cartRequest);
        
        String hashId = ""+cart.getItemId();
        try {
            stringRedisTemplate.opsForHash().increment(""+cart.getUserId(), hashId, cart.getQuantity());
            stringRedisTemplate.expire(""+cart.getUserId(), 10, TimeUnit.MINUTES);

            ItemResponse itemResponse = itemServiceClient.getItemById((long)cartRequest.getItemId()).getBody();

            if(cartItems.containsKey(cart.getUserId())){
                HashMap<Long,ItemResponse> map = cartItems.get(cart.getUserId());
                map.put((long)cart.getItemId(), itemResponse);

                cartItems.put(cart.getUserId(),map);
            }
            else {
                HashMap<Long,ItemResponse> map = new HashMap<>();
                map.put((long)cart.getItemId(), itemResponse);

                cartItems.put(cart.getUserId(),map);
            }

        }catch (Exception e){
            throw new HandleCartItemException("Failed to add item to cart.");
        }
        return true;
    }

    @Override
    public boolean removeItemFromCart(CartRequest cartRequest) {
        try{
          Long count = stringRedisTemplate.opsForHash().delete(""+cartRequest.getUserId(),""+cartRequest.getItemId());
          if(count>0){
              cartItems.get(""+cartRequest.getUserId()).remove(""+cartRequest.getItemId());
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
    public CartResponse getAllCartItems(int  userId) {
        Map<Object,Object> cartItemsMap = stringRedisTemplate.opsForHash().entries(""+userId);


        CartResponse cartResponse = new CartResponse();
        cartResponse.setUserId(userId);

        //set dynamic cart Id
        cartResponse.setCartId(1);

        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        float amount = 0;
        for (Map.Entry<Object,Object> entry : cartItemsMap.entrySet()) {
          long itemId = Long.parseLong(entry.getKey().toString());
          int quantity = Integer.parseInt(entry.getValue().toString());

          ItemResponse itemResponse = cartItems.get(""+userId).get(itemId);

          CartItemResponse cartItemResponse = new CartItemResponse();
          cartItemResponse.setItemId(itemId);
          cartItemResponse.setQuantity(quantity);
          cartItemResponse.setItemName(itemResponse.getItemName());
          cartItemResponse.setPrice((float) itemResponse.getAmount());
          cartItemResponse.setTotalPrice((float) itemResponse.getAmount()*itemResponse.getQuantity());

          cartItemResponses.add(cartItemResponse);

          amount += cartItemResponse.totalPrice;

        }


        cartResponse.setItems(cartItemResponses);
        cartResponse.setTotalAmount(amount);


        return cartResponse;
    }
}
