package com.store.productapi.service

import com.store.core.domain.product.ProductCreateDomain
import com.store.core.domain.product.ProductDomain
import com.store.core.domain.product.ProductUpdateDomain
import com.store.productapi.entity.ProductEntity
import com.store.productapi.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService (private val productRepository: ProductRepository){

    fun getProducts() : List<ProductDomain> {
        val productEntitys : List<ProductEntity> = productRepository.findAll()

        return productEntitys.map { it.toDomain() }
            .toList()
    }

    fun getById(id : Long) : ProductDomain {
        val product: ProductEntity = productRepository.findByIdOrNull(id) ?: throw NoSuchElementException()

        return product.toDomain()
    }

    fun getByName(name : String) : ProductDomain {
        val product: ProductEntity = productRepository.findByName(name) ?: throw NoSuchElementException()

        return product.toDomain()
    }

    fun register(productCreateDomain: ProductCreateDomain) : ProductDomain {
        val productDomain = ProductDomain.from(productCreateDomain.name, productCreateDomain.price)

        duplicateProductCheck(productDomain);

        val productEntity: ProductEntity = ProductEntity.from(productDomain)

        return productRepository.save(productEntity)
            .toDomain()
    }

    private fun duplicateProductCheck(productDomain : ProductDomain) {
        val isProduct = productRepository.existsByName(productDomain.name)
        check(!isProduct) {"이미 등록된 상품입니다."}
    }

    @Transactional
    open fun update(id: Long, productUpdateDomain: ProductUpdateDomain) : ProductDomain {
        val productDomain: ProductDomain = getById(id)
        productDomain.update(productUpdateDomain)

        return productRepository.save(ProductEntity.from(productDomain))
            .toDomain()
    }


}
