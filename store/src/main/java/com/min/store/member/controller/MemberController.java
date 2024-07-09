package com.min.store.member.controller;


import com.min.store.common.http.ApiResponse;
import com.min.store.member.domain.MemberDomain;
import com.min.store.member.domain.MemberSignUpDomain;
import com.min.store.member.dto.MemberResponseDto;
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
        MemberDomain memberDomain = memberService.getById(id);
        return ResponseEntity.ok()
                .body(ApiResponse.success(
                        MemberResponseDto.from(memberDomain))
                );
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody MemberSignUpDomain memberSignUpDomain) {
        MemberDomain memberDomain = memberService.register(memberSignUpDomain);
        return ResponseEntity.ok()
                .body(ApiResponse.success(
                        MemberResponseDto.from(memberDomain))
                );
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ApiResponse> getMemberOrders(@PathVariable Long id) {
        MemberDomain memberDomain = memberService.getMemberOrders(id);
        return ResponseEntity.ok()
                .body(ApiResponse.success(
                        MemberResponseDto.from(memberDomain))
                );
    }

}
