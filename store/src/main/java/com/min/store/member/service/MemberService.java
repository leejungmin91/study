package com.min.store.member.service;


import com.min.store.common.http.ApiResponse;
import com.min.store.member.domain.Member;
import com.min.store.member.dto.request.SignUpRequestDto;
import com.min.store.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public ApiResponse getMember(Long id){
        Member member = memberRepository.findById(id)
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

        memberRepository.save(member);

        return ApiResponse.success();
    }
}
