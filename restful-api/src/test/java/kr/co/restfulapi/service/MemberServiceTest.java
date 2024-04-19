package kr.co.restfulapi.service;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class MemberServiceTest {

    private final MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService){
        this.memberService = memberService;
    }

    @BeforeEach
    void beforeEach1(){
        회원_가입_테스트();
    }

    @Test
    void 전체_회원_조회_테스트() {
        List<MemberDto> members = memberService.findAll();
        members.forEach(m->log.info(m.toString()));
        assertThat(members).isNotEmpty();
    }

    @Test
    void 회원_조회_테스트() {
        Long findMbrNo = 1L;
        MemberDto memberDto = memberService.findById(findMbrNo);
        assertThat(memberDto.getMbrNo()).isEqualTo(findMbrNo);
    }

    @Test
    void 회원_가입_테스트() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .id("min")
                .name("이정민")
                .password("1234")
                .build();

        MemberDto joinMember = memberService.join(signUpRequest);

        MemberDto findMember = memberService.findById(joinMember.getMbrNo());

        assertThat(joinMember).isEqualTo(findMember);
    }
}
