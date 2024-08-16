package com.store.memberapi.controller;


import com.store.core.http.ApiResponse;
import com.store.memberapi.domain.MemberDomain;
import com.store.memberapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
