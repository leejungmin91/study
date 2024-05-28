package com.min.store.member.controller;

import com.min.store.member.dto.request.SignUpRequestDto;
import com.min.store.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        memberService.register(signUpRequestDto);
        return ResponseEntity.ok()
                .build();
    }

}
