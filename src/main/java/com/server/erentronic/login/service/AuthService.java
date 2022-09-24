package com.server.erentronic.login.service;

import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.repository.MemberRepository;
import com.server.erentronic.login.dto.LoginRequest;
import com.server.erentronic.login.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private final MemberRepository memberRepository;
	
	private final JwtTokenProvider jwtTokenProvider;

	public String login(LoginRequest request) {
		//todo 커스텀 에러로 변경
		String email = request.getEmail();
		String password = request.getPassword();

		Member member = memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);

		member.validatePassword(password);

		return jwtTokenProvider.makeJwtAccessToken(member);
	}
}
