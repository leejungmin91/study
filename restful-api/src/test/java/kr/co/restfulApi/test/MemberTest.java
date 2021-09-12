package kr.co.restfulApi.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kr.co.restfulApi.model.Member;
import kr.co.restfulApi.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
class MemberTest {
	
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void save() throws Exception {
		String id = "min";
		String name = "이정민";
		final Member member = new Member(id, name);				
		final Member savedMember = memberRepository.save(member);
		
		System.out.println("saveTest :: "+savedMember);
	}
	
	@Test
	void update() throws Exception {
		String id = "min";
		String name = "이정민";
		final Member member = new Member(id, name);				
		final Member savedMember = memberRepository.save(member);
		
		System.out.println("updateTest 1 :: "+member);
		
		String udtId = "min2";
		String udtName = "이정민2";
		final Member member2 = new Member(udtId, udtName);				
		
		Optional<Member> e = memberRepository.findById(savedMember.getMbrNo());
		
		if(e.isPresent()) {
			e.get().setMbrNo(savedMember.getMbrNo());
			e.get().setId(member2.getId());
			e.get().setName(member2.getName());
			final Member updateMember = memberRepository.save(e.get());
			
			System.out.println("updateTest 2 :: "+updateMember);
		}
		
	}

}
