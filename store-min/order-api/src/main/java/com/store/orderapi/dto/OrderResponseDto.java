package com.store.orderapi.dto;

import com.store.orderapi.domain.OrderDomain;
import com.store.orderapi.domain.OrderItemDomain;
import com.store.core.domain.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderResponseDto {
    private Long id;
    private String name;
    private List<OrderItemDomain> orderItems;
    private OrderStatus status;

    public static OrderResponseDto from(OrderDomain orderDomain) {
        return OrderResponseDto.builder()
                .id(orderDomain.getId())
                .name(orderDomain.getMember().getName())
                .orderItems(orderDomain.getOrderItems())
                .status(orderDomain.getStatus())
                .build();
    }
}
