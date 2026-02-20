package com.shopping_cart.app.http.response;

import lombok.Setter;

@Setter
public class ItemResponse {

  public Long itemId;

  public String itemName;

  public String category;

  public String description;

  public double amount;

  public float rating;
}
