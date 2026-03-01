package com.shopping_cart.app.common.enums;

public enum CategoryType {
  MOBILES("MOBILES"),
  LAPTOP("LAPTOP"),
  TELEVISION("TELEVISION"),
  WASHING_MACHINE("WASHING MACHINE");

  public String value;

  CategoryType(String value) {
    this.value = value;
  }

  public String getCategoryType() {
    return value;
  }
}
