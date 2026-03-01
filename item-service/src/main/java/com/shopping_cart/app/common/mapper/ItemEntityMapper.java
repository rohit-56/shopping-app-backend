package com.shopping_cart.app.common.mapper;

import com.shopping_cart.app.common.enums.CategoryType;
import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.model.ItemEntity;
import java.util.function.Function;

public class ItemEntityMapper {

  public static final Function<ItemRequest, ItemEntity> fromItemRequestToItemEntity =
      itemRequest -> {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemName(itemRequest.getItemName());
        itemEntity.setCategoryType(CategoryType.valueOf(itemRequest.getCategoryType()));
        itemEntity.setAmount(itemRequest.getAmount());
        itemEntity.setDescription(itemRequest.getDescription());
        itemEntity.setRating(itemRequest.getRating());
        return itemEntity;
      };

  public static final Function<ItemEntity, ItemResponse> fromItemEntityToItemResponse =
      itemEntity -> {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemId(itemEntity.getId());
        itemResponse.setItemName(itemEntity.getItemName());
        itemResponse.setDescription(itemEntity.getDescription());
        itemResponse.setCategory(itemEntity.categoryType.getCategoryType());
        itemResponse.setAmount(itemEntity.getAmount());
        itemResponse.setRating(itemEntity.getRating());
        return itemResponse;
      };
}
