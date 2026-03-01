package com.shopping_cart.app.service.impl;

import com.shopping_cart.app.common.enums.CategoryType;
import com.shopping_cart.app.common.mapper.ItemEntityMapper;
import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.model.ItemEntity;
import com.shopping_cart.app.repository.ItemEntityRepository;
import com.shopping_cart.app.service.IItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements IItemService {

  private ItemEntityRepository itemEntityRepository;

  public ItemServiceImpl(ItemEntityRepository itemEntityRepository) {
    this.itemEntityRepository = itemEntityRepository;
  }

  @Override
  public ItemResponse createItem(ItemRequest itemRequest) {
    ItemEntity itemEntity = ItemEntityMapper.fromItemRequestToItemEntity.apply(itemRequest);
    ItemEntity savedItem = itemEntityRepository.save(itemEntity);
    ItemResponse itemResponse = ItemEntityMapper.fromItemEntityToItemResponse.apply(savedItem);
    return itemResponse;
  }

  @Override
  public ItemResponse getItemById(Long itemId) {
    Optional<ItemEntity> itemEntity = itemEntityRepository.findById(itemId);
    if (itemEntity.isEmpty()) {
      return null;
    } else {
      ItemResponse itemResponse =
          ItemEntityMapper.fromItemEntityToItemResponse.apply(itemEntity.get());
      return itemResponse;
    }
  }

  @Override
  public List<ItemResponse> getItemsByCategory(String category) {
    List<ItemEntity> itemEntityList =
        itemEntityRepository.findByCategoryType(CategoryType.valueOf(category));
    List<ItemResponse> itemResponseList =
        itemEntityList.stream().map(ItemEntityMapper.fromItemEntityToItemResponse).toList();
    return itemResponseList;
  }

  @Override
  public void deleteItemById(Long itemId) {
    itemEntityRepository.deleteById(itemId);
  }

  @Override
  public ItemResponse updateItem(ItemRequest itemRequest) {
    // To do
    return null;
  }
}
