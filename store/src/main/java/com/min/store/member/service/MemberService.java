package com.min.store.member.service;


import com.min.store.common.exception.ApiException;
import com.min.store.common.http.ApiCode;
import com.min.store.common.http.ApiResponse;
import com.min.store.member.domain.Member;
import com.min.store.member.domain.MemberId;
import com.min.store.member.dto.request.SignUpRequestDto;
import com.min.store.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public ApiResponse getMember(Long id){
        Member member = memberRepository.findById(new MemberId(id))
                .orElseThrow(EntityNotFoundException::new);

        return ApiResponse.success(member.toMemberResponseDto());
    }

    @Transactional
    public ApiResponse register(SignUpRequestDto signUpRequestDto){
        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .build();

        duplicateMemberCheck(member);

        memberRepository.save(member);

        return ApiResponse.success();
    }

    private void duplicateMemberCheck(Member member){
        boolean isMember = memberRepository.existsByEmail(member.getEmail());
        if(!isMember) throw new IllegalStateException("이미 가입된 회원입니다.");
    }

    public Member currentMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object details = authentication.getDetails();
            if(details instanceof Map map){
                return Member.builder()
                        .id(Long.parseLong(String.valueOf(map.get("id"))))
                        .email((String) map.get("email"))
                        .name((String) map.get("name"))
                        .build();
            }
        }

        throw new ApiException(ApiCode.ACCESS_DENIED);
    }
}
