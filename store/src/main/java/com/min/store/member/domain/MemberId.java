package com.min.store.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MemberId implements Serializable {

    @Column(name="member_id")
    private Long id;

    public MemberId(Long id){
        this.id = id;
    }

}
