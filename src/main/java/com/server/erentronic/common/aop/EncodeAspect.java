package com.server.erentronic.common.aop;

import com.server.erentronic.auth.dto.LoginRequest;
import com.server.erentronic.common.member.dto.SignUpRequest;
import com.server.erentronic.common.utils.Sha256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class EncodeAspect {

	private final Sha256 sha256;

	@Before("@annotation(com.server.erentronic.common.aop.EncodePassword)")
	public void encodePassword(JoinPoint joinPoint) {

		Object requestDto = joinPoint.getArgs()[0];

		if (requestDto instanceof LoginRequest) {
			encodePassword((LoginRequest) requestDto);
		}

		if (requestDto instanceof SignUpRequest) {
			encodePassword((SignUpRequest) requestDto);
		}
	}

	private void encodePassword(LoginRequest loginRequest) {
		String password = loginRequest.getPassword();
		log.info("origin password: {}", password);
		loginRequest.setPassword(sha256.encrypt(password));
	}

	private void encodePassword(SignUpRequest signUpRequest) {
		String password = signUpRequest.getPassword();
		log.info("origin password: {}", password);
		signUpRequest.setPassword(sha256.encrypt(password));
	}
}
