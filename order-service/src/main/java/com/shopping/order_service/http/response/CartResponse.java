package com.shopping.order_service.http.response;

import java.util.List;

public class CartResponse {

    public int cartId;

    public String userId;

    public List<CartItemResponse> items;

    public float totalAmount;

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
