package com.shopping_cart.app.service;

import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.model.ItemEntity;

import java.util.List;

public interface IItemService {

    ItemResponse createItem(ItemRequest itemRequest);

    ItemResponse getItemById(Long itemId);

    List<ItemResponse> getItemsByCategory(String category);

    void deleteItemById(Long itemId);

    ItemResponse updateItem(ItemRequest itemRequest);


}
