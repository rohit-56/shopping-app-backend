package com.shopping.order_service.entity;

public class Cart {

    public int cartId;

    public long itemId;

    public String userId;
    
    public int quantity;

    public Cart(){

    }
    public Cart(int cartId, int itemId, String userId, int quantity){
        this.cartId = cartId;
        this.itemId = itemId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
