package com.store.memberapi.controller;


import com.store.core.http.ApiResponse;
import com.store.memberapi.domain.MemberDomain;
import com.store.memberapi.domain.MemberSignUpDomain;
import com.store.memberapi.dto.MemberResponseDto;
import com.store.memberapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
