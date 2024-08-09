package com.min.store.product.controller

import com.min.store.common.http.ApiResponse
import com.min.store.product.domain.ProductCreateDomainKotlin
import com.min.store.product.domain.ProductDomainKotlin
import com.min.store.product.dto.ProductResponseDto
import com.min.store.product.service.ProductKotlinService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product-kotlin")
class ProductKotlinController(private val productService: ProductKotlinService) {

    @GetMapping
    fun getProducts(): ResponseEntity<ApiResponse> {
        val productDomains: List<ProductDomainKotlin> = productService.getProducts()
        val productResponseDtos: List<ProductResponseDto> = productDomains.map { ProductResponseDto.from(it) }

        return ResponseEntity.ok()
            .body(ApiResponse.success(productResponseDtos))
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ApiResponse> {
        val productDomain: ProductDomainKotlin = productService.getById(id)
        return ResponseEntity.ok()
            .body(
                ApiResponse
                    .success(ProductResponseDto.from(productDomain))
            )
    }

    @PostMapping
    fun register(@RequestBody productCreateDomain: ProductCreateDomainKotlin): ResponseEntity<ApiResponse> {
        val productDomain: ProductDomainKotlin = productService.register(productCreateDomain)
        return ResponseEntity.ok()
            .body(
                ApiResponse
                    .success(ProductResponseDto.from(productDomain))
            )
    }
}
