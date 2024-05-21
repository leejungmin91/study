package kr.co.restfulapi.dto;

import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.entity.Product;
import lombok.*;

@Getter
@Setter
@Builder
public class ProductCreateDto {

    private Long productId;

    private String productName;

    private Long price;

    private Long discountRate;

    public Product toEntity(){
        return Product.builder()
                .productId(this.productId)
                .productName(this.productName)
                .price(this.price)
                .discountRate(this.discountRate)
                .build();
    }
}
