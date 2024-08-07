package com.min.store.product.service

import com.min.store.product.domain.ProductCreateDomainKotlin
import com.min.store.product.domain.ProductDomainKotlin
import com.min.store.product.domain.ProductUpdateDomain
import com.min.store.product.entity.ProductEntity
import com.min.store.product.repository.ProductRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException
import javax.persistence.EntityNotFoundException

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
open class ProductKotlinService (private val productRepository: ProductRepository){

    open fun getProducts() : List<ProductDomainKotlin> {
        val productEntitys : List<ProductEntity> = productRepository.findAll()

        return productEntitys.stream()
            .map(ProductEntity::toDomainKotlin)
            .toList()
    }

    open fun getById(id : Long) : ProductDomainKotlin {
        val product: ProductEntity = productRepository.findById(id)
            .orElseThrow { EntityNotFoundException() }

        return  product.toDomainKotlin()
    }

    open fun register(productCreateDomain:ProductCreateDomainKotlin) : ProductDomainKotlin {
        val productDomain = ProductDomainKotlin.from(productCreateDomain.name, productCreateDomain.price)

        duplicateProductCheck(productDomain);

        val productEntity:ProductEntity = ProductEntity.fromKotline(productDomain)

        return productRepository.save(productEntity)
            .toDomainKotlin()
    }

    private fun duplicateProductCheck(productDomain : ProductDomainKotlin) {
        val isProduct = productRepository.existsByName(productDomain.name)
        if(isProduct) throw IllegalStateException("이미 등록된 상품입니다.")
    }

    @Transactional
    open fun update(id: Long, productUpdateDomain: ProductUpdateDomain) : ProductDomainKotlin {
        val productDomain:ProductDomainKotlin = getById(id);
        productDomain.update(productUpdateDomain)

        return productRepository.save(ProductEntity.fromKotline(productDomain))
            .toDomainKotlin()
    }


}
