package com.shopping_cart.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.model.ItemEntity;
import com.shopping_cart.app.repository.ItemEntityRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

  @Mock private ItemEntityRepository itemEntityRepository;

  @InjectMocks private ItemServiceImpl itemService;

  @Test
  void createItem_shouldSaveEntityAndReturnResponse() {
    ItemRequest request = buildRequest();
    ItemEntity savedEntity = buildEntity(42L, "MOBILE");

    given(itemEntityRepository.save(any(ItemEntity.class))).willReturn(savedEntity);

    ItemResponse response = itemService.createItem(request);

    assertThat(response).isNotNull();
    assertThat(response.getItemId()).isEqualTo(42L);
    assertThat(response.getItemName()).isEqualTo(request.itemName);
    assertThat(response.getCategory()).isEqualTo(request.categoryType);
    assertThat(response.getAmount()).isEqualTo(request.amount);
    assertThat(response.getQuantity()).isEqualTo(request.quantity);
    assertThat(response.getRating()).isEqualTo(request.rating);
  }

  @Test
  void getItemById_whenItemExists_shouldReturnItemResponse() {
    ItemEntity itemEntity = buildEntity(10L, "LAPTOP");
    given(itemEntityRepository.findById(10L)).willReturn(Optional.of(itemEntity));

    ItemResponse response = itemService.getItemById(10L);

    assertThat(response).isNotNull();
    assertThat(response.getItemId()).isEqualTo(10L);
    assertThat(response.getCategory()).isEqualTo("LAPTOP");
  }

  @Test
  void getItemById_whenItemDoesNotExist_shouldReturnNull() {
    given(itemEntityRepository.findById(anyLong())).willReturn(Optional.empty());

    ItemResponse response = itemService.getItemById(999L);

    assertThat(response).isNull();
  }

  @Test
  void getItemsByCategory_shouldReturnMappedItemResponses() {
    ItemEntity itemEntity = buildEntity(5L, "TELEVISION");
    given(itemEntityRepository.findByCategoryType("TELEVISION")).willReturn(List.of(itemEntity));

    List<ItemResponse> responses = itemService.getItemsByCategory("TELEVISION");

    assertThat(responses).hasSize(1);
    assertThat(responses.get(0).getCategory()).isEqualTo("TELEVISION");
    assertThat(responses.get(0).getItemId()).isEqualTo(5L);
  }

  @Test
  void deleteItemById_shouldInvokeRepositoryDelete() {
    itemService.deleteItemById(7L);

    verify(itemEntityRepository).deleteById(7L);
  }

  @Test
  void updateItem_shouldReturnNullBecauseNotImplemented() {
    ItemResponse response = itemService.updateItem(buildRequest());

    assertThat(response).isNull();
  }

  @Test
  void getAllItems_shouldReturnPagedResponses() {
    ItemEntity entity1 = buildEntity(1L, "MOBILE");
    ItemEntity entity2 = buildEntity(2L, "LAPTOP");
    given(itemEntityRepository.findAll(any(Pageable.class)))
        .willReturn(new PageImpl<>(List.of(entity1, entity2)));

    List<ItemResponse> responses = itemService.getAllItems(0, 10);

    assertThat(responses).hasSize(2);
    assertThat(responses).extracting(ItemResponse::getItemId).containsExactly(1L, 2L);
  }

  private ItemRequest buildRequest() {
    ItemRequest request = new ItemRequest();
    request.itemName = "Test Item";
    request.categoryType = "MOBILE";
    request.description = "Test description";
    request.amount = 49.99;
    request.rating = 4.5f;
    request.quantity = 5;
    request.itemImage = null;
    return request;
  }

  private ItemEntity buildEntity(Long id, String category) {
    ItemEntity entity = new ItemEntity();
    entity.setId(id);
    entity.setItemName("Test Item");
    entity.setCategoryType(category);
    entity.setDescription("Test description");
    entity.setAmount(49.99);
    entity.setRating(4.5f);
    entity.setQuantity(5);
    return entity;
  }
}
