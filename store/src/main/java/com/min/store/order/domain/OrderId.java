package com.min.store.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderId implements Serializable {

    @Column(name="order_id")
    private Long id;

    public OrderId(Long id){
        this.id = id;
    }

}
