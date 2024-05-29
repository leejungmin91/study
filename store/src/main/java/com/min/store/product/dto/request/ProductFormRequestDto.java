package com.min.store.product.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductFormRequestDto {

    @NotNull(message = "상품명은 필수입니다.")
    private String name;
}
