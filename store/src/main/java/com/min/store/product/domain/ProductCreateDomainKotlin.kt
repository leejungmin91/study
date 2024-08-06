package com.min.store.product.domain

import lombok.Builder

@Builder
class ProductCreateDomainKotlin(val name: String, val price: Long) {
}
