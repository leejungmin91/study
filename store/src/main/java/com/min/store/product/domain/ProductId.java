package com.min.store.product.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
public class ProductId implements Serializable {

    @Column(name="id")
    private Long id;

    public ProductId(Long id){
        this.id = id;
    }

}
