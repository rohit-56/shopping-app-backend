package com.shopping_cart.app.model;

import com.shopping_cart.app.common.enums.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  public String itemName;

  public CategoryType categoryType;

  public String description;

  public double amount;

  public float rating;

  // public MultipartFile itemImage;
}
