package com.min.store.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductUpdateDomain {

    private final String name;
    private final Long price;

    @Builder
    public ProductUpdateDomain(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
