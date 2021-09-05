package kr.co.restfulApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.restfulApi.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
