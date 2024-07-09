package com.min.store.member.controller;


import com.min.store.common.http.ApiResponse;
import com.min.store.member.domain.MemberDomain;
import com.min.store.member.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthContoller {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody MemberDomain memberDomain){
        HttpHeaders httpHeaders = authenticationService.authenticate(memberDomain);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(ApiResponse.success());
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse> reissue(HttpServletRequest request){
        String newToken = authenticationService.reissueToken(request);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer "+newToken)
                .body(ApiResponse.success());
    }

}
