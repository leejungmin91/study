package com.min.store.member.repository;

import com.min.store.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {
    Optional<MemberEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
