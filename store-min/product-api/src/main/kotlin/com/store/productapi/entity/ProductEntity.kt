package com.store.productapi.entity

import com.store.core.domain.product.ProductDomain
import javax.persistence.*

@Table(name = "PRODUCT")
@Entity
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Long,
) {

    companion object {
        fun from(productDomain: ProductDomain): ProductEntity {
            return ProductEntity(
                id= productDomain.id,
                name = productDomain.name,
                price = productDomain.price
            )
        }
    }

    fun toDomain() : ProductDomain {
        return ProductDomain(id = id, name = name, price = price)
    }
}
