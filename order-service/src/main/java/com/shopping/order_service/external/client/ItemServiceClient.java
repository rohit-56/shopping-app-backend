package com.shopping.order_service.external.client;

import com.shopping.order_service.external.client.http.ItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "apigateway",url = "http://localhost:4003/api")
public interface ItemServiceClient {

    @GetMapping("/items/getItemById/{itemId}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long itemId);
}
