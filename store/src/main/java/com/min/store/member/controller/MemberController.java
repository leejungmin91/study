package com.min.store.member.controller;


import com.min.store.common.http.ApiResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMember(@PathVariable Long id) {
        ApiResponse apiResponse = memberService.getMember(id);
        return ResponseEntity.ok()
                .body(apiResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        ApiResponse apiResponse = memberService.register(signUpRequestDto);
        return ResponseEntity.ok()
                .body(apiResponse);
    }

}
