package com.min.store.member.controller;

import com.min.store.member.service.AuthenticationService;
import com.min.store.member.dto.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthContoller {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto loginRequestDto){
        return authenticationService.authenticate(loginRequestDto);
    }

}
