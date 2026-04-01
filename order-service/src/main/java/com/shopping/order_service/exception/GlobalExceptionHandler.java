package com.shopping.order_service.exception;

import com.shopping.order_service.http.request.CartRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HandleCartItemException.class)
   public ResponseEntity<String> handleCartItemException(HandleCartItemException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
   }
}
