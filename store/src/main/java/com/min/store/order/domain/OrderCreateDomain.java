package com.min.store.order.domain;

import com.min.store.member.domain.MemberDomain;
import com.min.store.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateDomain {

    private final MemberDomain member;
    private final List<OrderItemDomain> orderItems;
    private final OrderStatus status;

    @Builder
    public OrderCreateDomain(MemberDomain member, List<OrderItemDomain> orderItems, OrderStatus status) {
        this.member = member;
        this.orderItems = orderItems;
        this.status = status;
    }

}
