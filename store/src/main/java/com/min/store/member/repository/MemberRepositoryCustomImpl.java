package com.min.store.member.repository;

import com.min.store.member.entity.MemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.min.store.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<MemberEntity> findByName(String name) {
        return Optional.ofNullable(query.selectFrom(member)
                .where(nameEq(name))
                .fetchFirst());
    }

    private BooleanExpression nameEq(String name) {
        return name != null ? member.name.eq(name) : null;
    }
}
