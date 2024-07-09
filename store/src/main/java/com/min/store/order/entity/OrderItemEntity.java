package com.min.store.order.entity;

import com.min.store.order.domain.OrderItemDomain;
import com.min.store.product.entity.ProductEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "T_ORDER_ITEM")
public class OrderItemEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private int orderPrice;

    private int count;

    public static OrderItemEntity from(OrderItemDomain orderItemDomain) {
        OrderItemEntity orderEntity = new OrderItemEntity();
        orderEntity.id = orderItemDomain.getId();
        orderEntity.product = ProductEntity.from(orderItemDomain.getProduct());
        orderEntity.order = OrderEntity.from(orderItemDomain.getOrder());
        orderEntity.orderPrice = orderItemDomain.getOrderPrice();
        orderEntity.count = orderItemDomain.getCount();
        return orderEntity;
    }

    public OrderItemDomain toDomain() {
        return OrderItemDomain.builder()
                .id(id)
                .product(product.toDomain())
                .order(order.toDomain())
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }
}
