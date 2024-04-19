package kr.co.restfulapi.rest;

import java.util.List;
import java.util.Optional;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.restfulapi.entity.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restapi")
@RequiredArgsConstructor
public class RestApiController {

	private final MemberService memberService;
		
	@GetMapping("/welcome")
	public String welcome() {
		
		log.info("welcome");
		
		return "welcome";
	}
	
	@GetMapping
	public ResponseEntity<List<MemberDto>> getAllMembers() {
		List<MemberDto> member = memberService.findAll();
		return ResponseEntity.ok(member);
	}
	
	@GetMapping(value = "/{mbrNo}")
	public ResponseEntity<MemberDto> getMember(@PathVariable("mbrNo") Long mbrNo) {
		MemberDto memberDto = memberService.findById(mbrNo);
		return ResponseEntity.ok(memberDto);
	}
	
	@PutMapping
	public ResponseEntity<MemberDto> updateMember(MemberDto memberDto) {
		memberService.updateName(memberDto);
		return ResponseEntity.ok()
				.build();
	}

}
