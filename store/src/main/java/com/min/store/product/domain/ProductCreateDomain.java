package com.min.store.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateDomain {

    private final String name;
    private final Long price;

    @Builder
    public ProductCreateDomain(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
