package kr.co.restfulapi.service;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.SignUpRequest;
import kr.co.restfulapi.entity.Member;
import kr.co.restfulapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 전체 회원 조회
     */
    public List<MemberDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 회원 정보 검색
     */
    public MemberDto findById(Long mbrNo) {
        Member member = memberRepository.findById(mbrNo)
                .orElseThrow(() -> new IllegalArgumentException("회원번호를 확인해주세요."));
        return MemberDto.toDto(member);
    }

    /**
     * 회원 정보 수정
     */
    public void updateName(MemberDto memberDto) {
        memberRepository.findById(memberDto.getMbrNo())
                .ifPresent(m -> m.setName(memberDto.getName()));
    }

    /**
     * 회원가입
     */
    public MemberDto join(SignUpRequest request) {
        duplicateMemberValidation(request.toEntity());
        Member member = memberRepository.save(request.toEntity());
        return MemberDto.toDto(member);
    }

    private void duplicateMemberValidation(Member member) {
        if(memberRepository.existsByEmail(member.getEmail())) throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

}
