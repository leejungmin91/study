package com.min.store.member.service;

import com.min.store.member.domain.Member;
import com.min.store.member.dto.request.SignUpRequestDto;
import com.min.store.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(SignUpRequestDto signUpRequestDto){
        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .build();

        memberRepository.save(member);
    }
}
