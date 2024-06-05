package com.min.store.product.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.min.store.product.domain.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponseDto {
    private ProductId productId;
    private String name;
    private Long price;
}
