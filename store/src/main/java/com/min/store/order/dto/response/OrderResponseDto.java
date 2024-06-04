package com.min.store.order.dto.response;

import com.min.store.member.domain.MemberId;
import com.min.store.order.domain.OrderId;
import com.min.store.order.domain.OrderItem;
import com.min.store.order.domain.OrderStatus;
import com.min.store.order.domain.Orderer;
import com.min.store.product.dto.response.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private OrderId orderId;
    private Orderer orderer;
    private List<ProductResponseDto> orderItems;
    private OrderStatus status;
}
