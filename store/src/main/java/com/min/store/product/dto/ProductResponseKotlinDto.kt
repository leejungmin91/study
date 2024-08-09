package com.min.store.product.dto

import com.min.store.product.domain.ProductDomainKotlin

class ProductResponseKotlinDto (
    val id: Long?,
    val name: String?,
    val price: Long?
    ) {

    companion object {
        fun from(productDomain: ProductDomainKotlin): ProductResponseKotlinDto {
            return ProductResponseKotlinDto(id = productDomain.id, name = productDomain.name, price = productDomain.price)
        }
    }
}
