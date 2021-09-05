package kr.co.restfulApi.service;

import java.util.List;
import java.util.Optional;

import kr.co.restfulApi.model.Member;

public interface MemberService {

	public List<Member> findAll() throws Exception;
	
	public Optional<Member> findById(Long mbrNo) throws Exception;
	
	public Member saveMember(Member member) throws Exception;
	
	public void updateById(Long mbrNo, Member member) throws Exception;
}
