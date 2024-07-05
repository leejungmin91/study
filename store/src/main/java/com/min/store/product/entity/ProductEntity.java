package com.min.store.product.entity;

import com.min.store.product.domain.ProductDomain;
import lombok.*;

import javax.persistence.*;

@Getter
@Table(name = "PRODUCT")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    public static ProductEntity from(ProductDomain productDomain) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.id = productDomain.getId();
        productEntity.name = productDomain.getName();
        productEntity.price = productDomain.getPrice();
        return productEntity;
    }

    public ProductDomain toDomain() {
        return ProductDomain.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }

}
