package com.shopping.order_service.common.mapper;

import com.shopping.order_service.entity.Cart;
import com.shopping.order_service.external.client.http.ItemResponse;
import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CartMapper {

    public static final Function<CartRequest, Cart> fromCartRequestToCartEntity = cartRequest ->{
       Cart cart = new Cart();
       //Currently setting cart id static value to 1, will update
       cart.setCartId(1);
       cart.setItemId(cartRequest.getItemId());
       cart.setUserId(cartRequest.getUserId());
       cart.setQuantity(cartRequest.getQuantity());
       return cart;
    } ;


}
