package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.entity.Product;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "productId")
@ToString
public class ProductDto {

    private Long productId;

    private String productName;

    private Long price;

    private Long discountRate;

    public static ProductDto toDto(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .discountRate(product.getDiscountRate())
                .build();
    }
}
