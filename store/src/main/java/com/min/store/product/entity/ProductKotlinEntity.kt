package com.min.store.product.entity

import com.min.store.product.domain.ProductDomainKotlin
import javax.persistence.*

@Table(name = "PRODUCT")
@Entity
class ProductKotlinEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Long,
) {

    companion object {
        fun from(productDomainKotlin: ProductDomainKotlin): ProductKotlinEntity {
            return ProductKotlinEntity(
                id= productDomainKotlin.id,
                name = productDomainKotlin.name,
                price = productDomainKotlin.price
            )
        }
    }

    fun toDomain() : ProductDomainKotlin {
        return ProductDomainKotlin(id = id, name = name, price = price)
    }
}
