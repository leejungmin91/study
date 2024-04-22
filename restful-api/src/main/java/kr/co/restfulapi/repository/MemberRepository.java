package kr.co.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.restfulapi.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    /**
     * 중복 회원 확인 (EMAIL)
     */
    boolean existsByEmail(String email);

}
