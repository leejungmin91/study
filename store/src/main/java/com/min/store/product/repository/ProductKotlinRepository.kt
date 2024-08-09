package com.min.store.product.repository

import com.min.store.product.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductKotlinRepository : JpaRepository<ProductEntity, Long> {
    fun findByName(name: String): ProductEntity?
    fun existsByName(name: String): Boolean
}
