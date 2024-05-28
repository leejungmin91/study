package com.min.store.member.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.min.store.member.domain.Member;
import com.min.store.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemUserDetails implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("{} - authenticate -> 인증", this.getClass());

        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found Member"));

        log.info("{} ::  member info", member);

        return Member.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .build();
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
