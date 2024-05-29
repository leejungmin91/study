package com.min.store.product.domain;

import com.min.store.member.dto.response.MemberResponseDto;
import com.min.store.product.dto.response.ProductResponseDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "PRODUCT")
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public ProductResponseDto toProductResponseDto(){
        return ProductResponseDto.builder()
                .id(id)
                .name(name)
                .build();
    }

}
