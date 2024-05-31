package com.min.store.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ProductId implements Serializable {

    @Column(name="id")
    private Long id;

    public ProductId(Long id){
        this.id = id;
    }

}
