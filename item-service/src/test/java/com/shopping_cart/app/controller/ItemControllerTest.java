package com.shopping_cart.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.service.IItemService;
import com.shopping_cart.app.service.ImageService;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

  @Mock private IItemService itemService;
  @Mock private ImageService imageService;

  @InjectMocks private ItemController itemController;

  @Test
  void createItem_shouldSaveImageAndReturnCreatedResponse() throws IOException {
    MockMultipartFile file =
        new MockMultipartFile("itemImage", "image.png", "image/png", "abc".getBytes());
    ItemRequest request = buildRequest(file);
    ItemResponse expectedResponse = buildResponse(100L, "MOBILE");

    given(imageService.saveImageToStorage(anyString(), any(MultipartFile.class)))
        .willReturn("image.png");
    given(itemService.createItem(request)).willReturn(expectedResponse);

    ResponseEntity<ItemResponse> response = itemController.createItem(request);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(expectedResponse);
    verify(imageService).saveImageToStorage(anyString(), eq(file));
    verify(itemService).createItem(request);
  }

  @Test
  void getItemById_shouldReturnOkResponse() {
    ItemResponse expectedResponse = buildResponse(12L, "LAPTOP");
    given(itemService.getItemById(12L)).willReturn(expectedResponse);

    ResponseEntity<ItemResponse> response = itemController.getItemById(12L);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isSameAs(expectedResponse);
  }

  @Test
  void getItemsByCategory_shouldReturnCategoryList() {
    ItemResponse expectedResponse = buildResponse(14L, "TELEVISION");
    given(itemService.getItemsByCategory("TELEVISION")).willReturn(List.of(expectedResponse));

    ResponseEntity<List<ItemResponse>> response = itemController.getItemsByCategory("TELEVISION");

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(1);
    assertThat(response.getBody().get(0).getCategory()).isEqualTo("TELEVISION");
  }

  @Test
  void deleteItemById_shouldReturnAcceptedStatus() {
    ResponseEntity<String> response = itemController.deleteItemById(20L);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    assertThat(response.getBody()).contains("20");
    verify(itemService).deleteItemById(20L);
  }

  @Test
  void getAllCategories_shouldReturnSupportedCategories() {
    ResponseEntity<List<String>> response = itemController.getAllCategories();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody())
        .containsExactly("MOBILE", "LAPTOP", "TELEVISION", "WASHING MACHINE");
  }

  @Test
  void getAllItems_shouldForwardPaginationToService() {
    ItemResponse response1 = buildResponse(1L, "MOBILE");
    ItemResponse response2 = buildResponse(2L, "LAPTOP");
    given(itemService.getAllItems(0, 10)).willReturn(List.of(response1, response2));

    ResponseEntity<List<ItemResponse>> response = itemController.getAllItems(0, 10);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(2);
    assertThat(response.getBody()).containsExactly(response1, response2);
  }

  private ItemRequest buildRequest(MultipartFile imageFile) {
    ItemRequest request = new ItemRequest();
    request.itemName = "Unit Test Item";
    request.categoryType = "MOBILE";
    request.description = "Unit test description";
    request.amount = 10.0;
    request.rating = 4.0f;
    request.quantity = 1;
    request.itemImage = imageFile;
    return request;
  }

  private ItemResponse buildResponse(Long itemId, String category) {
    ItemResponse response = new ItemResponse();
    response.setItemId(itemId);
    response.setItemName("Unit Test Item");
    response.setCategory(category);
    response.setDescription("Unit test description");
    response.setAmount(10.0);
    response.setRating(4.0f);
    response.setQuantity(1);
    return response;
  }
}
