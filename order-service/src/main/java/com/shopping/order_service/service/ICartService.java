package com.shopping.order_service.service;

import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;


public interface ICartService {

    public boolean addItemToCart(CartRequest cartRequest);

    public boolean removeItemFromCart(CartRequest cartRequest);

    public CartResponse getAllCartItems(int userId);
}
