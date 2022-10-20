package com.server.erentronic.auth.interceptor;

import static com.server.erentronic.auth.AuthConst.*;

import com.server.erentronic.auth.jwt.JwtTokenProvider;
import com.server.erentronic.common.exception.JwtTokenException;
import com.server.erentronic.common.message.ErrorDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) {

//		String header = request.getHeader(AUTHORIZATION);
		String header = request.getHeader(ACCESS_TOKEN);
		Long memberId = parsingJwtToken(response, header);
		request.setAttribute(MEMBER_ID, memberId);

		return true;
	}

	private Long parsingJwtToken(HttpServletResponse response, String header) {
		if (header.isBlank() || !header.startsWith(BEARER)) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			throw new JwtTokenException(ErrorDetail.EMPTY_BEARER_JWT_TOKEN);
		}

		Claims claims = null;

		try {
			String jwtToken = header.replaceFirst(BEARER, Strings.EMPTY).trim();
			claims = jwtTokenProvider.verifyToken(jwtToken);
		} catch (ExpiredJwtException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			throw new JwtTokenException(ErrorDetail.EXPIRED_JWT_TOKEN);
		} catch (JwtException e) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			throw new JwtTokenException(ErrorDetail.NOT_VALID_JWT_TOKEN);
		}

		return Long.valueOf(claims.getAudience());
	}
}
