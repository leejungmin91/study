package com.min.store.product.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "PRODUCT")
@Builder
@Entity
@IdClass(ProductId.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    public void updateProduct(String name, Long price) {
        this.name = name;
        this.price = price;
    }

}
