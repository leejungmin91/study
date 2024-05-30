package com.min.store.product.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductFormRequestDto {

    private Long id;

    @NotNull(message = "상품명은 필수입니다.")
    private String name;

    @NotNull(message = "가격은 필수입니다.")
    private Long price;
}
