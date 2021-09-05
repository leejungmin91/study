package kr.co.restfulApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.restfulApi.model.Member;
import kr.co.restfulApi.repository.MemberRepository;
import kr.co.restfulApi.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
	@Override
	public Optional<Member> findById(Long mbrNo) {
		return memberRepository.findById(mbrNo);
	}
	
	@Override
	public Member saveMember(Member member) {
		return memberRepository.save(member);		
	}
	
	@Override
	public void updateById(Long mbrNo, Member member) {
		Optional<Member> e = memberRepository.findById(mbrNo);
		
		if(e.isPresent()) {
			e.get().setMbrNo(member.getMbrNo());
			e.get().setId(member.getId());
			e.get().setName(member.getName());
			memberRepository.save(member);
		}
	}

}
