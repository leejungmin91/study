package com.store.memberapi.entity;

import com.store.memberapi.domain.MemberDomain;
import com.store.orderapi.entity.OrderEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "MEMBER")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();

    public static MemberEntity from(MemberDomain memberDomain) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = memberDomain.getId();
        memberEntity.email = memberDomain.getEmail();
        memberEntity.name = memberDomain.getName();
        memberEntity.password = memberDomain.getPassword();
        memberEntity.orders = memberDomain.getOrders() != null ? memberDomain.getOrders()
                .stream()
                .map(OrderEntity::from)
                .collect(Collectors.toList())
                : null;
        return memberEntity;
    }

    public MemberDomain toDomain() {
        return MemberDomain.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .orders(orders != null ? orders.stream()
                        .map(OrderEntity::toDomain)
                        .toList()
                        : null
                )
                .build();
    }
}
