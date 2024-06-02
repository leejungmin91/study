package com.min.store.order.domain;

import com.min.store.product.domain.ProductId;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "T_ORDER")
@Builder
@Entity
public class Order {

    @EmbeddedId
    private OrderId orderId;

    @Embedded
    private Orderer orderer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "T_ORDER_ITEMS", joinColumns = @JoinColumn(name = "order_id"))
    @OrderColumn(name = "item_idx")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void cancel() {
        if (status == OrderStatus.ORDER) {
            this.status = OrderStatus.CANCEL;
        }
    }

}
