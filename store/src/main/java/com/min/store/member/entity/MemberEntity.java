package com.min.store.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.min.store.member.domain.MemberDomain;
import com.min.store.product.domain.ProductDomain;
import com.min.store.product.entity.ProductEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Table(name = "MEMBER")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @JsonIgnore
    private String password;

    public static MemberEntity from(MemberDomain memberDomain) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = memberDomain.getId();
        memberEntity.email = memberDomain.getEmail();
        memberEntity.name = memberDomain.getName();
        memberEntity.password = memberDomain.getPassword();
        return memberEntity;
    }

    public MemberDomain toDomain() {
        return MemberDomain.builder()
                .id(id)
                .email(email)
                .name(name)
                .build();
    }
}
