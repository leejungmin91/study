package kr.co.restfulapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;*/

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Long quantity;

    public OrderItem(Product product, Long quantity){
        this.product = product;
        this.quantity = quantity;
    }
}
