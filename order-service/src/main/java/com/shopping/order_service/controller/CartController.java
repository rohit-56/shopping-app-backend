package com.shopping.order_service.controller;

import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;
import com.shopping.order_service.service.ICartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<String> addItemToCart(@RequestBody CartRequest cartRequest) {
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
       boolean result = cartService.removeItemFromCart(cartRequest);
       if (result) {
          return new ResponseEntity<>("Success", HttpStatus.OK);
       }
       else {
          return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCartItems(@PathVariable int userId) {
      CartResponse cartResponse = cartService.getAllCartItems(userId);
      return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

}
