package com.store.product.api.repository

import com.store.product.api.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
    fun findByName(name: String): ProductEntity?
    fun existsByName(name: String): Boolean
}
