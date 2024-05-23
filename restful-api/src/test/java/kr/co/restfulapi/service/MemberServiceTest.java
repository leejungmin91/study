package kr.co.restfulapi.service;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.SignUpRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Slf4j
class MemberServiceTest {

    private final MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @BeforeEach
    void beforeEach() {
        SignUpRequestDto signUpRequest = SignUpRequestDto.builder()
                .email("min@test.com")
                .name("이정민")
                .password("1234")
                .build();

        memberService.join(signUpRequest);
    }

    @Test
    void 회원_조회_테스트() {
        Long findMbrNo = 1L;
        MemberDto memberDto = memberService.findById(findMbrNo);
        assertThat(memberDto.getMbrNo()).isEqualTo(findMbrNo);
    }

    @Test
    void 회원_가입_테스트() {
        SignUpRequestDto signUpRequest = SignUpRequestDto.builder()
                .email("min2@test.com")
                .name("이정민")
                .password("1234")
                .build();

        MemberDto joinMember = memberService.join(signUpRequest);

        MemberDto findMember = memberService.findById(joinMember.getMbrNo());

        assertThat(joinMember).isEqualTo(findMember);
    }

    @Test
    void 중복_가입_테스트() {
        SignUpRequestDto signUpRequest = SignUpRequestDto.builder()
                .email("min@test.com")
                .name("이정민")
                .password("1234")
                .build();

        assertThatThrownBy(() -> memberService.join(signUpRequest)).isInstanceOf(IllegalStateException.class).hasMessage("이미 존재하는 회원입니다.");
    }
}
