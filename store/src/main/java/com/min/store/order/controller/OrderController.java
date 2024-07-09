package com.min.store.order.controller;


import com.min.store.common.http.ApiResponse;
import com.min.store.order.domain.OrderCreateDomain;
import com.min.store.order.domain.OrderDomain;
import com.min.store.order.service.OrderService;
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
