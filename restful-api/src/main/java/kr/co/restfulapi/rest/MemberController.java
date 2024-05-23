package kr.co.restfulapi.rest;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.SignUpRequestDto;
import kr.co.restfulapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/{mbrNo}")
	public ResponseEntity<MemberDto> findMember(@PathVariable Long mbrNo) {
		MemberDto memberDto = memberService.findById(mbrNo);
		return ResponseEntity.ok(memberDto);
	}

    @PostMapping
	public ResponseEntity<MemberDto> join(@Valid SignUpRequestDto request) {
		MemberDto memberDto = memberService.join(request);
		return ResponseEntity.ok(memberDto);
	}

}
