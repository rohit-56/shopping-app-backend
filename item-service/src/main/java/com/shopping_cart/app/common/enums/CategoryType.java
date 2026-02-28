package com.shopping_cart.app.common.enums;

public enum CategoryType {
    MOBILES("MOBILES"),
    LAPTOP("LAPTOP"),
    TELEVISION("TELEVISION"),
    WASHING_MACHINE("WASHING_MACHINE");

    private String value;

    CategoryType(String value) {
        this.value = value;
    }

    public String getCategoryType(CategoryType categoryType) {
        return categoryType.value;
    }
}
