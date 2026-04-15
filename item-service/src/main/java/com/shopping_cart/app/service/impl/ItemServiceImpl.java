package com.shopping_cart.app.service.impl;

import com.shopping_cart.app.common.mapper.ItemEntityMapper;
import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.model.ItemEntity;
import com.shopping_cart.app.repository.ItemEntityRepository;
import com.shopping_cart.app.service.IItemService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemServiceImpl implements IItemService {

  private ItemEntityRepository itemEntityRepository;

  public ItemServiceImpl(ItemEntityRepository itemEntityRepository) {
    this.itemEntityRepository = itemEntityRepository;
  }

  @Override
  public ItemResponse createItem(ItemRequest itemRequest) {
    log.info("Creating item: {}", itemRequest);
    ItemEntity itemEntity = ItemEntityMapper.fromItemRequestToItemEntity.apply(itemRequest);
    ItemEntity savedItem = itemEntityRepository.save(itemEntity);
    ItemResponse itemResponse = ItemEntityMapper.fromItemEntityToItemResponse.apply(savedItem);
    log.info("Created item with id: {}", savedItem.getId());
    return itemResponse;
  }

  @Override
  public ItemResponse getItemById(Long itemId) {
    log.info("Fetching item by id: {}", itemId);
    Optional<ItemEntity> itemEntity = itemEntityRepository.findById(itemId);
    if (itemEntity.isEmpty()) {
      log.warn("Item not found for id: {}", itemId);
      return null;
    } else {
      ItemResponse itemResponse =
          ItemEntityMapper.fromItemEntityToItemResponse.apply(itemEntity.get());
      log.debug("Found item: {}", itemResponse);
      return itemResponse;
    }
  }

  @Override
  public List<ItemResponse> getItemsByCategory(String category) {
    log.info("Fetching items by category: {}", category);
    List<ItemEntity> itemEntityList = itemEntityRepository.findByCategoryType(category);
    List<ItemResponse> itemResponseList =
        itemEntityList.stream().map(ItemEntityMapper.fromItemEntityToItemResponse).toList();
    log.debug("Found {} items for category {}", itemResponseList.size(), category);
    return itemResponseList;
  }

  @Override
  public void deleteItemById(Long itemId) {
    log.info("Deleting item by id: {}", itemId);
    itemEntityRepository.deleteById(itemId);
    log.debug("Deleted item by id: {}", itemId);
  }

  @Override
  public ItemResponse updateItem(ItemRequest itemRequest) {
    log.warn("updateItem is not implemented yet. Request: {}", itemRequest);
    // To do
    return null;
  }

  @Override
  public List<ItemResponse> getAllItems(int pageNumber, int pageSize) {
    log.info("Fetching all items");

    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());

    List<ItemEntity> itemEntityList = itemEntityRepository.findAll(pageable).getContent();

    return itemEntityList.stream().map(ItemEntityMapper.fromItemEntityToItemResponse).toList();
  }
}
