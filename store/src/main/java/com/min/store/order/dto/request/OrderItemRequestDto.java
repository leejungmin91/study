package com.min.store.order.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class OrderItemRequestDto {

    @NotEmpty(message = "상품번호는 필수입니다.")
    private Long productId;

    @NotEmpty(message = "상품 갯수는 필수입니다.")
    private int quantity;
}
