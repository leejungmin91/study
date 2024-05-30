package com.min.store.order.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderRequestDto {

    @NotEmpty(message = "상품리스트는 필수입니다.")
    private List<OrderItemRequestDto> orderItems;
}
