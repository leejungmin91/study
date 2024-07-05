package com.min.store.product.domain;

import lombok.*;

@Getter
public class ProductDomain {

    private final Long id;
    private final String name;
    private final Long price;

    @Builder
    public ProductDomain(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductDomain from(String name, Long price) {
        return ProductDomain.builder()
                .name(name)
                .price(price)
                .build();
    }

    public ProductDomain update(ProductUpdateDomain productUpdate) {
        return ProductDomain.builder()
                .id(id)
                .name(productUpdate.getName())
                .price(productUpdate.getPrice())
                .build();
    }

}
