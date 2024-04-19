package kr.co.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.restfulapi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
