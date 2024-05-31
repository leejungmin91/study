package com.min.store.member.repository;

import com.min.store.member.domain.Member;
import com.min.store.member.domain.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, MemberId> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
