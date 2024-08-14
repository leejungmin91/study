package com.min.store.product.service

import com.min.store.product.domain.ProductCreateDomain
import com.min.store.product.domain.ProductDomain
import com.min.store.product.domain.ProductUpdateDomain
import com.min.store.product.entity.ProductEntity
import com.min.store.product.repository.ProductRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
open class ProductService (private val productRepository: ProductRepository){

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

    fun register(productCreateDomain:ProductCreateDomain) : ProductDomain {
        val productDomain = ProductDomain.from(productCreateDomain.name, productCreateDomain.price)

        duplicateProductCheck(productDomain);

        val productEntity:ProductEntity = ProductEntity.from(productDomain)

        return productRepository.save(productEntity)
            .toDomain()
    }

    private fun duplicateProductCheck(productDomain : ProductDomain) {
        val isProduct = productRepository.existsByName(productDomain.name)
        if(isProduct) throw IllegalStateException("이미 등록된 상품입니다.")
    }

    @Transactional
    open fun update(id: Long, productUpdateDomain: ProductUpdateDomain) : ProductDomain {
        val productDomain:ProductDomain = getById(id)
        productDomain.update(productUpdateDomain)

        return productRepository.save(ProductEntity.from(productDomain))
            .toDomain()
    }


}
