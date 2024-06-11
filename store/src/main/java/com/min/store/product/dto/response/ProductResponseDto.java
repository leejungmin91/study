package com.min.store.product.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.min.store.product.domain.ProductId;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponseDto {
    private ProductId productId;
    private String name;
    private Long price;
    private Long orderPrice;
    private int quantity;
}
