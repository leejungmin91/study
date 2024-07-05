package com.min.store.product.controller;


import com.min.store.common.http.ApiResponse;
import com.min.store.product.domain.ProductCreateDomain;
import com.min.store.product.domain.ProductDomain;
import com.min.store.product.domain.ProductUpdateDomain;
import com.min.store.product.dto.ProductResponseDto;
import com.min.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getProducts() {
        List<ProductDomain> productDomains = productService.getProducts();
        List<ProductResponseDto> productResponseDtos = productDomains.stream()
                .map(ProductResponseDto::from)
                .toList();

        return ResponseEntity.ok()
                .body(ApiResponse
                        .success(productResponseDtos)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long id) {
        ProductDomain productDomain = productService.getById(id);
        return ResponseEntity.ok()
                .body(ApiResponse
                        .success(ProductResponseDto.from(productDomain))
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid ProductCreateDomain productCreateDomain) {
        ProductDomain productDomain = productService.register(productCreateDomain);
        return ResponseEntity.ok()
                .body(ApiResponse
                        .success(ProductResponseDto.from(productDomain))
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid ProductUpdateDomain productUpdateDomain) {
        ProductDomain productDomain = productService.update(id, productUpdateDomain);
        return ResponseEntity.ok()
                .body(ApiResponse
                        .success(ProductResponseDto.from(productDomain))
                );
    }

}
