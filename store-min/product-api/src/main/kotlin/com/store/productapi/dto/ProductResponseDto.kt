package com.store.productapi.dto

import com.store.core.domain.product.ProductDomain


class ProductResponseDto (
    val id: Long?,
    val name: String?,
    val price: Long?
    ) {

    companion object {
        fun from(productDomain: ProductDomain): ProductResponseDto {
            return ProductResponseDto(id = productDomain.id, name = productDomain.name, price = productDomain.price)
        }
    }
}
