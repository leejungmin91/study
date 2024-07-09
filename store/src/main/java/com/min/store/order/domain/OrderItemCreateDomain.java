package com.min.store.order.domain;

import com.min.store.product.domain.ProductDomain;
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
