package com.shopping.order_service.external.client.http;

public class ItemResponse {
    public Long itemId;

    public String itemName;

    public String category;

    public String description;

    public double amount;

    public float rating;

    public int quantity;

    public Long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public float getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }
}
