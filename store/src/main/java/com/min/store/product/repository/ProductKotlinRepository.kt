package com.min.store.product.repository

import com.min.store.product.entity.ProductEntity
import com.min.store.product.entity.ProductKotlinEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductKotlinRepository : JpaRepository<ProductKotlinEntity, Long> {
    fun findByName(name: String): ProductKotlinEntity?
    fun existsByName(name: String): Boolean
}
