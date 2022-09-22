package com.server.erentronic.common.controlleradvice;

import com.server.erentronic.common.member.dto.SignUpRequest;
import com.server.erentronic.common.utils.Sha256;
import java.lang.reflect.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class RequestBodyControllerAdvice implements RequestBodyAdvice {

	private final Sha256 sha256;

	@Override
	public boolean supports(final MethodParameter methodParameter, final Type targetType,
		final Class<? extends HttpMessageConverter<?>> converterType) {

		return targetType.getTypeName().equals(SignUpRequest.class.getTypeName());
	}

	@Override
	public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage,
		final MethodParameter parameter, final Type targetType,
		final Class<? extends HttpMessageConverter<?>> converterType) {

		return inputMessage;
	}

	@Override
	public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage,
		final MethodParameter parameter, final Type targetType,
		final Class<? extends HttpMessageConverter<?>> converterType) {

		final SignUpRequest signUpRequest = (SignUpRequest) body;

		//todo 비밀번호 정규식으로 체크!

		try {
			String encodedPassword = sha256.encrypt(signUpRequest.getPassword());
			signUpRequest.setPassword(encodedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return signUpRequest;
	}

	@Override
	public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage,
		final MethodParameter parameter, final Type targetType,
		final Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}
}
