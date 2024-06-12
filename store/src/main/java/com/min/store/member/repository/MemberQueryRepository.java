package com.min.store.member.repository;

import com.min.store.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.min.store.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory query;

    public Optional<Member> findByName(String name) {
        return Optional.ofNullable(query.selectFrom(member)
                .where(member.name.eq(name))
                .fetchFirst());
    }
}
