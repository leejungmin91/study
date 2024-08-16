package com.store.orderapi.entity;

import com.min.store.member.entity.MemberEntity;
import com.store.core.domain.order.OrderStatus;
import com.store.orderapi.domain.OrderDomain;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "T_ORDER")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static OrderEntity from(OrderDomain orderDomain) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.id = orderDomain.getId();
        orderEntity.member = MemberEntity.from(orderDomain.getMember());
        orderEntity.orderItems = orderDomain.getOrderItems()
                .stream()
                .map(OrderItemEntity::from)
                .toList();
        orderEntity.status = orderDomain.getStatus();
        return orderEntity;
    }

    public OrderDomain toDomain() {
        return OrderDomain.builder()
                .id(id)
                .member(member.toDomain())
                .orderItems(orderItems.stream()
                        .map(OrderItemEntity::toDomain)
                        .toList())
                .status(status)
                .build();
    }
}
