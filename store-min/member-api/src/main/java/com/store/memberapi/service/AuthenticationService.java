package com.store.memberapi.service;

import com.store.memberapi.config.JwtTokenProvider;
import com.store.memberapi.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@Slf4j
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public HttpHeaders authenticate(MemberDomain memberDomain){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDomain.getEmail(), memberDomain.getPassword());

        // security 인증 실행
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 토큰 생성 (param : 인증토큰)
        String jwtToken = jwtTokenProvider.createToken(authentication);
        // refresh 토큰 생성
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

        log.info("JWT issue ==> {}", jwtToken);

        ResponseCookie responseCookie = ResponseCookie.from("refreshToken",refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                //.domain("yourdomain.net")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer "+jwtToken);
        httpHeaders.set(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return httpHeaders;
    }

    public String reissueToken(HttpServletRequest request){
        return jwtTokenProvider.reissueToken(request);
    }

}
