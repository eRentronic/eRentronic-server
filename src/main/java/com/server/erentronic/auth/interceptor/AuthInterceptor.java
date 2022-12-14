package com.server.erentronic.auth.interceptor;

import static com.server.erentronic.auth.AuthConst.ACCESS_TOKEN;
import static com.server.erentronic.auth.AuthConst.MEMBER_ID;

import com.server.erentronic.auth.jwt.JwtTokenProvider;
import com.server.erentronic.common.exception.JwtTokenException;
import com.server.erentronic.common.message.ErrorDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

		String accessToken = request.getParameter(ACCESS_TOKEN);

		if (accessToken == null) {
			return true;
		}

		Long loginMemberId = parsingJwtToken(response, accessToken);
		request.setAttribute(MEMBER_ID, loginMemberId);

		return true;
	}

	private Long parsingJwtToken(HttpServletResponse response, String jwtToken) {
		try {
			Claims claims = jwtTokenProvider.verifyToken(jwtToken);
			return Long.valueOf(claims.getAudience());
		} catch (ExpiredJwtException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			throw new JwtTokenException(ErrorDetail.EXPIRED_JWT_TOKEN);
		} catch (JwtException e) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			throw new JwtTokenException(ErrorDetail.NOT_VALID_JWT_TOKEN);
		}
	}

//	private Long parsingJwtToken(HttpServletResponse response, String header) {
//		if (header.isBlank() || !header.startsWith(BEARER)) {
//			response.setStatus(HttpStatus.FORBIDDEN.value());
//			throw new JwtTokenException(ErrorDetail.EMPTY_BEARER_JWT_TOKEN);
//		}
//
//		Claims claims = null;
//
//		try {
//			String jwtToken = header.replaceFirst(BEARER, Strings.EMPTY).trim();
//			claims = jwtTokenProvider.verifyToken(jwtToken);
//		} catch (ExpiredJwtException e) {
//			response.setStatus(HttpStatus.UNAUTHORIZED.value());
//			throw new JwtTokenException(ErrorDetail.EXPIRED_JWT_TOKEN);
//		} catch (JwtException e) {
//			response.setStatus(HttpStatus.FORBIDDEN.value());
//			throw new JwtTokenException(ErrorDetail.NOT_VALID_JWT_TOKEN);
//		}
//
//		return Long.valueOf(claims.getAudience());
//	}
}
