package com.min.store.order.domain;

import com.min.store.member.domain.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Orderer {

    @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;

    public Orderer(MemberId memberId){
        this.memberId = memberId;
    }

    public Orderer(MemberId memberId, String name){
        this.memberId = memberId;
        this.name = name;
    }

}
