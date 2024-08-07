package com.min.store.product.dto;

import com.min.store.product.domain.ProductDomain;
import com.min.store.product.domain.ProductDomainKotlin;
import lombok.*;

@Getter
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
    /*private Long orderPrice;
    private int quantity;*/
    public static ProductResponseDto from(ProductDomain productDomain) {
        return ProductResponseDto.builder()
                .id(productDomain.getId())
                .name(productDomain.getName())
                .price(productDomain.getPrice())
                .build();
    }

    public static ProductResponseDto from(ProductDomainKotlin productDomain) {
        return ProductResponseDto.builder()
                .id(productDomain.getId())
                .name(productDomain.getName())
                .price(productDomain.getPrice())
                .build();
    }
}
