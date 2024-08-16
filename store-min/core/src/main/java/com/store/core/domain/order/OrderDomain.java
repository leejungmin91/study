package com.store.core.domain.order;

import com.store.core.domain.member.MemberDomain;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDomain {

    private final Long id;
    private final MemberDomain member;
    private final List<OrderItemDomain> orderItems;
    private final OrderStatus status;

    @Builder
    public OrderDomain(Long id, MemberDomain member, List<OrderItemDomain> orderItems, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.status = status;
    }

    public static OrderDomain from(MemberDomain member, List<OrderItemDomain> orderItems, OrderStatus status) {
        return OrderDomain.builder()
                .member(member)
                .orderItems(orderItems)
                .status(status)
                .build();
    }

}
