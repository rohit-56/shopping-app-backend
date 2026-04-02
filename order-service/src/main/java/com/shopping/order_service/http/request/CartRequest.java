package com.shopping.order_service.http.request;

import jakarta.validation.constraints.NotNull;

public class CartRequest {

    @NotNull(message = "User Id is Required")
    public String userId;

    @NotNull(message = "Item Id is Required")
    public long itemId;

    @NotNull(message = "Quantity is Required")
    public int quantity;

    public String getUserId() {
        return userId;
    }

    public long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
