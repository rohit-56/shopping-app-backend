package com.shopping.order_service.service;

import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;
import java.util.List;


public interface ICartService {

    public boolean addItemToCart(CartRequest cartRequest);

    public boolean removeItemFromCart(CartRequest cartRequest);

    public List<CartResponse> getAllCartItems(int userId);
}
