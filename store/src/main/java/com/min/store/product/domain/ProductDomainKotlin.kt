package com.min.store.product.domain

class ProductDomainKotlin (
    val id: Long?=null,
    val name: String,
    val price: Long
    ) {

    companion object {
        fun from(name: String, price: Long): ProductDomainKotlin {
            return ProductDomainKotlin(name = name, price = price)
        }
    }

    fun update(productUpdate: ProductUpdateDomain): ProductDomainKotlin {
        return ProductDomainKotlin(id, productUpdate.name, productUpdate.price)
    }
}
