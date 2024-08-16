package com.store.orderapi.controller;


import com.store.core.domain.order.OrderCreateDomain;
import com.store.core.domain.order.OrderDomain;
import com.store.core.http.ApiResponse;
import com.store.orderapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable Long id) {
        OrderDomain orderDomain = orderService.findOrder(id);
        return ResponseEntity.ok()
                .body(ApiResponse
                        .success(orderDomain)
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> order(@RequestBody OrderCreateDomain orderCreateDomain) {
        OrderDomain orderDomain = orderService.order(orderCreateDomain);
        return ResponseEntity.ok()
                .body(ApiResponse
                        .success(orderDomain));
    }

}
