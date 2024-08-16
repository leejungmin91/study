package com.store.productapi.controller

import com.store.core.domain.product.ProductCreateDomain
import com.store.core.domain.product.ProductDomain
import com.store.core.http.ApiResponse
import com.store.productapi.dto.ProductResponseDto
import com.store.productapi.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product-kotlin")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getProducts(): ResponseEntity<ApiResponse> {
        val productDomains: List<ProductDomain> = productService.getProducts()
        val productResponseDtos: List<ProductResponseDto> = productDomains.map { ProductResponseDto.from(it) }

        return ResponseEntity.ok()
            .body(ApiResponse.success(productResponseDtos))
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ApiResponse> {
        val productDomain: ProductDomain = productService.getById(id)
        return ResponseEntity.ok()
            .body(
                ApiResponse
                    .success(ProductResponseDto.from(productDomain))
            )
    }

    @PostMapping
    fun register(@RequestBody productCreateDomain: ProductCreateDomain): ResponseEntity<ApiResponse> {
        val productDomain: ProductDomain = productService.register(productCreateDomain)
        return ResponseEntity.ok()
            .body(
                ApiResponse
                    .success(ProductResponseDto.from(productDomain))
            )
    }
}
