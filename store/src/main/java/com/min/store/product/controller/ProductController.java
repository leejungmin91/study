package com.min.store.product.controller;


import com.min.store.common.http.ApiResponse;
import com.min.store.product.dto.request.ProductFormRequestDto;
import com.min.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long id) {
        ApiResponse apiResponse = productService.getProduct(id);
        return ResponseEntity.ok()
                .body(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid ProductFormRequestDto productFormRequestDto) {
        ApiResponse apiResponse = productService.register(productFormRequestDto);
        return ResponseEntity.ok()
                .body(apiResponse);
    }

}
