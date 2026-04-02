package com.shopping.order_service.http.response;

public class CartItemResponse {

    public long itemId;

    public String itemName;

    public int quantity;

    public double price;

    public double totalPrice;

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
