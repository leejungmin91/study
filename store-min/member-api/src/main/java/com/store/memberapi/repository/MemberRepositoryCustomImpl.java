package com.store.memberapi.repository;

import com.store.memberapi.entity.MemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.store.memberapi.entity.QMemberEntity.memberEntity;

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
