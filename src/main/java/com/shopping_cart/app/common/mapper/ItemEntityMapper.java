package com.shopping_cart.app.common.mapper;

import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.model.ItemEntity;

import java.util.function.Function;

public class ItemEntityMapper {

    public static final Function<ItemRequest, ItemEntity> fromItemRequestToItemEntity =
         itemRequest ->    {
                    ItemEntity itemEntity = new ItemEntity();
                    itemEntity.setItemName(itemRequest.getItemName());
                    itemEntity.setCategory(itemEntity.getCategory());
                    itemEntity.setAmount(itemEntity.getAmount());
                    itemEntity.setDescription(itemEntity.getDescription());
                    itemEntity.setRating(itemEntity.getRating());
                    return itemEntity;
            };

    public static final Function<ItemEntity, ItemResponse> fromItemEntityToItemResponse =
            itemEntity -> {
                 ItemResponse itemResponse = new ItemResponse();
                 itemResponse.setItemId(itemEntity.getId());
                 itemResponse.setItemName(itemEntity.getItemName());
                 itemResponse.setDescription(itemEntity.getDescription());
                 itemResponse.setCategory(itemEntity.getCategory());
                 itemResponse.setAmount(itemEntity.getAmount());
                 itemResponse.setRating(itemEntity.getRating());
                 return itemResponse;
            };
}
