package com.shopping.order_service.service.impl;

import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;
import com.shopping.order_service.service.ICartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Override
    public boolean addItemToCart(CartRequest cartRequest) {
        return false;
    }

    @Override
    public boolean removeItemFromCart(CartRequest cartRequest) {
        return false;
    }

    @Override
    public List<CartResponse> getAllCartItems(int  userId) {
        return List.of();
    }
}
