package com.min.store.member.repository;

import com.min.store.member.entity.MemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.min.store.member.entity.QMemberEntity.memberEntity;
import static com.min.store.order.entity.QOrderEntity.orderEntity;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<MemberEntity> findOrderById(Long id) {
        return Optional.ofNullable(query.selectFrom(memberEntity)
                .leftJoin(memberEntity.orders).fetchJoin()
                .where(idEq(id))
                .fetchFirst());
    }

    private BooleanExpression idEq(Long id) {
        return id != null ? memberEntity.id.eq(id) : null;
    }
}
