package com.server.erentronic.common.member.controller;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.member.dto.SignUpRequest;
import com.server.erentronic.common.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	public CUDResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
		return memberService.singUp(signUpRequest);
	}
}
