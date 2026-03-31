package com.shopping.order_service.controller;

import com.shopping.order_service.http.request.CartRequest;
import com.shopping.order_service.http.response.CartResponse;
import com.shopping.order_service.service.ICartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<String> addItemToCart(CartRequest cartRequest) {

    }

    @DeleteMapping("/deleteItemFromCart")
    public ResponseEntity<String> removeItemFromCart(CartRequest cartRequest) {

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCartItems(@PathVariable int userId) {
      return cartService.getAllCartItems(userId);
    }

}
