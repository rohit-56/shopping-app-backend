package com.shopping.order_service.controller;

import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;
import com.shopping.order_service.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    private ICartService cartService;

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<String> addItemToCart(@RequestBody CartRequest cartRequest) {
      log.info("Received request to add item to cart.");
      boolean result = cartService.addItemToCart(cartRequest);
      if (result) {
          return new ResponseEntity<>("Success", HttpStatus.CREATED);
      }
      else {
          return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
      }
    }

    @DeleteMapping("/deleteItemFromCart")
    public ResponseEntity<String> removeItemFromCart(@RequestBody CartRequest cartRequest) {
       log.info("Received request to delete item from cart.");
       boolean result = cartService.removeItemFromCart(cartRequest);
       if (result) {
          return new ResponseEntity<>("Success", HttpStatus.OK);
       }
       else {
          return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCartItems(@PathVariable String userId) {
      log.info("Received request to get all items from cart.");
      CartResponse cartResponse = cartService.getAllCartItems(userId);
      return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

}
