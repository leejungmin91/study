package com.min.store.order.domain;

import com.min.store.product.domain.ProductId;
import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderItem {

    @Embedded
    private ProductId product;

    @ManyToOne
    @JoinColumn(name = "order")
    private Order order;

    @Column
    private int orderPrice;

    @Column
    private int quantity;

}
