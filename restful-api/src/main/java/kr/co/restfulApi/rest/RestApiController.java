package kr.co.restfulApi.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.restfulApi.model.Member;
import kr.co.restfulApi.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("member")
public class RestApiController {
	
	@Autowired
	MemberService memberService;
		
	@GetMapping("/welcome")
	public String welcome() {
		
		log.info("welcome");
		
		return "welcome";
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Member>> getAllMembers() throws Exception {
		List<Member> member = memberService.findAll();
		return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Member> getMember(@PathVariable("mbrNo") Long mbrNo) throws Exception {
		Optional<Member> member = memberService.findById(mbrNo);
		return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Member> save(Member member) throws Exception {
		return new ResponseEntity<Member>(memberService.saveMember(member), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{mbrNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Member> updateMember(@PathVariable("mbrNo") Long mbrNo, Member member) throws Exception {
		memberService.updateById(mbrNo, member);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}

}
