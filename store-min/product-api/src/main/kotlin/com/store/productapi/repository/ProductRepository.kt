package com.store.productapi.repository

import com.store.productapi.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
    fun findByName(name: String): ProductEntity?
    fun existsByName(name: String): Boolean
}
