package com.shopping_cart.app.http.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ItemRequest {

  public String itemName;

  public String category;

  public String description;

  public double amount;

  public float rating;

  public MultipartFile itemImage;
}
