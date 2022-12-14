package com.server.erentronic.common.member.controller;

import com.server.erentronic.auth.annotation.LoginId;
import com.server.erentronic.common.member.dto.MemberResponse;
import com.server.erentronic.common.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	private final MemberService memberService;

	@GetMapping
	public MemberResponse getMemberDetail(@LoginId Long id) {
		return memberService.getMemberDetail(id);
	}
}
