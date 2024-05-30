package com.min.store.order.controller;


import com.min.store.common.http.ApiResponse;
import com.min.store.order.dto.request.OrderRequestDto;
import com.min.store.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /*@GetMapping
    public ResponseEntity<ApiResponse> getOrders() {
        ApiResponse apiResponse = productService.getProducts();
        return ResponseEntity.ok()
                .body(apiResponse);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable Long id) {
        ApiResponse apiResponse = orderService.findOrder(id);
        return ResponseEntity.ok()
                .body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> order(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        ApiResponse apiResponse = orderService.order(orderRequestDto);
        return ResponseEntity.ok()
                .body(apiResponse);
    }

    /*@PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid ProductFormRequestDto productFormRequestDto) {
        ApiResponse apiResponse = productService.update(productFormRequestDto);
        return ResponseEntity.ok()
                .body(apiResponse);
    }*/

}
