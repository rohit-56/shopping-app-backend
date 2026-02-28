package com.shopping_cart.app.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ItemRequest {

  @NotBlank(message = "Item Name is Required")
  public String itemName;

  @NotBlank(message = "Category is Required")
  public String categoryType;

  public String description;

  @NotBlank(message = "Amount is Required")
  public double amount;

  public float rating;

  public MultipartFile itemImage;
}
