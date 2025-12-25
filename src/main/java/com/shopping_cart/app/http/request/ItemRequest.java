package com.shopping_cart.app.http.request;

import lombok.Getter;

@Getter
public class ItemRequest {

    public String itemName;

    public String category;

    public String description;

    public double amount;

    public float rating;

}
