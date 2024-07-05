package com.min.store.order.domain;

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

    @Column
    private Long orderPrice;

    @Column
    private int quantity;

}
