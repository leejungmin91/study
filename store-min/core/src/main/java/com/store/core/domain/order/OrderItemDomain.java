package com.store.core.domain.order;

import com.store.core.domain.product.ProductDomain;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItemDomain {

    private final Long id;
    private final ProductDomain product;
    private final OrderDomain order;
    private final int orderPrice;
    private final int count;

    @Builder
    public OrderItemDomain(Long id, ProductDomain product, OrderDomain order, int orderPrice, int count) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItemDomain from(ProductDomain product, OrderDomain order, int orderPrice, int count) {
        return OrderItemDomain.builder()
                .product(product)
                .order(order)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }
}
