package com.shopping_cart.app.controller;

import com.shopping_cart.app.http.request.ItemRequest;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.service.IItemService;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController implements Serializable {

  private IItemService itemService;

  public ItemController(IItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping(value = "/create-item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ItemResponse> createItem(@ModelAttribute ItemRequest itemRequest) {
  //  System.out.println(itemRequest.getItemImage().getName());
    ItemResponse itemResponse = itemService.createItem(itemRequest);
    return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
  }

  @GetMapping("/getItemById/{itemId}")
  public ResponseEntity<ItemResponse> getItemById(@PathVariable Long itemId) {
    ItemResponse itemResponse = itemService.getItemById(itemId);
    return new ResponseEntity<>(itemResponse, HttpStatus.OK);
  }

  @GetMapping("/getItemByCategory")
  public ResponseEntity<List<ItemResponse>> getItemsByCategory(@RequestParam String category) {
    List<ItemResponse> itemResponseList = itemService.getItemsByCategory(category);
    return new ResponseEntity<>(itemResponseList, HttpStatus.OK);
  }

  @DeleteMapping("/deleteItem/{itemId}")
  public ResponseEntity<String> deleteItemById(@PathVariable Long itemId) {
    itemService.deleteItemById(itemId);
    return new ResponseEntity<>("Item Deleted for Id : " + itemId, HttpStatus.ACCEPTED);
  }
}
