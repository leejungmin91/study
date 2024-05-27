package kr.co.restfulapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "PRODUCT")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long price;

    private Long discountRate;

    public void updateProduct(String productName, Long price, Long discountRate) {
        this.productName = productName;
        this.price = price;
        this.discountRate = discountRate;
    }
}
