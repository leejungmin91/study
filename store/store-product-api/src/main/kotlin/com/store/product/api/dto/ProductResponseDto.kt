package com.store.product.api.dto

import com.store.product.api.domain.ProductDomain

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
