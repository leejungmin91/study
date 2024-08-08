package com.min.store.product.domain;

import lombok.Builder;

public class ProductUpdateDomain {

    private final String name;
    private final Long price;

    @Builder
    public ProductUpdateDomain(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
