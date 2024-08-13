package com.min.store.product.service

import com.min.store.product.domain.ProductCreateDomainKotlin
import com.min.store.product.domain.ProductDomainKotlin
import com.min.store.product.domain.ProductUpdateDomain
import com.min.store.product.domain.ProductUpdateDomainKotlin
import com.min.store.product.entity.ProductKotlinEntity
import com.min.store.product.repository.ProductKotlinRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
open class ProductKotlinService (private val productRepository: ProductKotlinRepository){

    fun getProducts() : List<ProductDomainKotlin> {
        val productEntitys : List<ProductKotlinEntity> = productRepository.findAll()

        return productEntitys.map { it.toDomain() }
            .toList()
    }

    fun getById(id : Long) : ProductDomainKotlin {
        val product: ProductKotlinEntity = productRepository.findByIdOrNull(id) ?: throw NoSuchElementException()

        return product.toDomain()
    }

    fun getByName(name : String) : ProductDomainKotlin {
        val product: ProductKotlinEntity = productRepository.findByName(name) ?: throw NoSuchElementException()

        return product.toDomain()
    }

    fun register(productCreateDomain:ProductCreateDomainKotlin) : ProductDomainKotlin {
        val productDomain = ProductDomainKotlin.from(productCreateDomain.name, productCreateDomain.price)

        duplicateProductCheck(productDomain);

        val productEntity:ProductKotlinEntity = ProductKotlinEntity.from(productDomain)

        return productRepository.save(productEntity)
            .toDomain()
    }

    private fun duplicateProductCheck(productDomain : ProductDomainKotlin) {
        val isProduct = productRepository.existsByName(productDomain.name)
        if(isProduct) throw IllegalStateException("이미 등록된 상품입니다.")
    }

    @Transactional
    open fun update(id: Long, productUpdateDomain: ProductUpdateDomainKotlin) : ProductDomainKotlin {
        val productDomain:ProductDomainKotlin = getById(id);
        productDomain.update(productUpdateDomain)

        return productRepository.save(ProductKotlinEntity.from(productDomain))
            .toDomain()
    }


}
