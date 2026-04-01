package com.shopping.order_service.entity;

public class Cart {

    public int cartId;

    public int itemId;

    public int userId;
    
    public int quantity;

    public Cart(){

    }
    public Cart(int cartId, int itemId, int userId, int quantity){
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
