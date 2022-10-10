package com.server.erentronic.auth.service;

import com.server.erentronic.common.aop.EncodePassword;
import com.server.erentronic.common.exception.NoSuchMemberException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.repository.MemberRepository;
import com.server.erentronic.auth.dto.LoginRequest;
import com.server.erentronic.auth.jwt.JwtTokenProvider;
import com.server.erentronic.common.message.ErrorDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthService {

	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@EncodePassword
	public String login(LoginRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();

		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new NoSuchMemberException(ErrorDetail.NO_SUCH_MEMBER_WITH_EMAIL));

		member.validatePassword(password);

		return jwtTokenProvider.makeJwtAccessToken(member);
	}
}
