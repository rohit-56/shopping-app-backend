package com.shopping.order_service.http.request;

import jakarta.validation.constraints.NotNull;

public class CartRequest {

    @NotNull(message = "User Id is Required")
    public int userId;

    @NotNull(message = "Item Id is Required")
    public int itemId;

    @NotNull(message = "Quantity is Required")
    public int quantity;

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
