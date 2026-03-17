package com.shopping_cart.app.controller;

import com.shopping_cart.app.common.enums.CategoryType;
import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.service.IItemService;
import com.shopping_cart.app.service.ImageService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController implements Serializable {

  private IItemService itemService;

  public ItemController(IItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping(value = "/create-item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ItemResponse> createItem(@ModelAttribute ItemRequest itemRequest)
      throws IOException {

    log.info("Create item request: {}", itemRequest.toString());

    ImageService imageService = new ImageService();
    imageService.saveImageToStorage(
        "C:\\Users\\Diya Bharadwaj\\Downloads", itemRequest.getItemImage());
    ItemResponse itemResponse = itemService.createItem(itemRequest);
    return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
  }

  @GetMapping("/getItemById/{itemId}")
  public ResponseEntity<ItemResponse> getItemById(@PathVariable Long itemId) {
    log.info("Get item by id: {}", itemId);
    ItemResponse itemResponse = itemService.getItemById(itemId);
    return new ResponseEntity<>(itemResponse, HttpStatus.OK);
  }

  @GetMapping("/getItemByCategory")
  public ResponseEntity<List<ItemResponse>> getItemsByCategory(@RequestParam String category) {
    log.info("Get items by category: {}", category);
    List<ItemResponse> itemResponseList = itemService.getItemsByCategory(category);
    return new ResponseEntity<>(itemResponseList, HttpStatus.OK);
  }

  @DeleteMapping("/deleteItem/{itemId}")
  public ResponseEntity<String> deleteItemById(@PathVariable Long itemId) {
    log.info("Delete item by id: {}", itemId);
    itemService.deleteItemById(itemId);
    return new ResponseEntity<>("Item Deleted for Id : " + itemId, HttpStatus.ACCEPTED);
  }

  @GetMapping("/getAllCategory")
  public ResponseEntity<List<String>> getAllCategories() {
    log.info("Get all categories");
    CategoryType[] list = CategoryType.values();
    List<String> categoryList = new ArrayList<>();
    for (CategoryType category : list) {
      categoryList.add(category.getCategoryType());
    }
    return new ResponseEntity<>(categoryList, HttpStatus.OK);
  }

  @GetMapping("/getItems")
  public ResponseEntity<List<ItemResponse>> getAllItems(
      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
    log.info("Get all items");
    List<ItemResponse> itemResponseList = itemService.getAllItems(pageNumber, limit);
    return new ResponseEntity<>(itemResponseList, HttpStatus.OK);
  }
}
