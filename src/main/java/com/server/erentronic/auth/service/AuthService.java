package com.server.erentronic.auth.service;

import com.server.erentronic.common.exception.NoMemberException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.repository.MemberRepository;
import com.server.erentronic.auth.dto.LoginRequest;
import com.server.erentronic.auth.jwt.JwtTokenProvider;
import com.server.erentronic.common.message.ErrorDetail;
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
		String email = request.getEmail();
		String password = request.getPassword();

		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new NoMemberException(ErrorDetail.NO_SUCH_MEMBER_WITH_EMAIL));

		member.validatePassword(password);

		return jwtTokenProvider.makeJwtAccessToken(member);
	}
}
