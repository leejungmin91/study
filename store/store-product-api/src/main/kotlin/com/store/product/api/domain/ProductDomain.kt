package com.store.product.api.domain

class ProductDomain (
    val id: Long?=null,
    val name: String,
    val price: Long
    ) {

    companion object {
        fun from(name: String, price: Long): ProductDomain {
            return ProductDomain(name = name, price = price)
        }
    }

    fun update(productUpdate: ProductUpdateDomain): ProductDomain {
        return ProductDomain(id, productUpdate.name, productUpdate.price)
    }
}
