package kr.co.restfulapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "ORDER")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbrNo")
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(Product product, Long quantity){
        OrderItem orderItem = new OrderItem(product, quantity);
        this.orderItems.add(orderItem);
    }


}
