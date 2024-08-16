package com.store.core.domain.order;

import com.store.core.domain.product.ProductDomain;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItemCreateDomain {

    private final ProductDomain product;
    private final OrderDomain order;
    private final int orderPrice;
    private final int count;

    @Builder
    public OrderItemCreateDomain(ProductDomain product, OrderDomain order, int orderPrice, int count) {
        this.product = product;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
