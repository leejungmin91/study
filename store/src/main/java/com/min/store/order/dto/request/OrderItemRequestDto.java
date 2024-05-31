package com.min.store.order.dto.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class OrderItemRequestDto {

    @NotNull(message = "상품번호는 필수입니다.")
    private Long productId;

    @Min(value = 1, message = "상품 갯수는 1개 이상이어야 합니다.")
    private int quantity;

    @NotNull(message = "상품가격은 필수입니다.")
    private int price;
}
