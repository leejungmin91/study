package com.min.store.order.dto.response;

import com.min.store.order.domain.OrderId;
import com.min.store.order.domain.OrderStatus;
import com.min.store.order.domain.Orderer;
import com.min.store.product.dto.ProductResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private OrderId orderId;
    private Orderer orderer;
    private List<ProductResponseDto> orderItems = new ArrayList<>();
    private OrderStatus status;
}
