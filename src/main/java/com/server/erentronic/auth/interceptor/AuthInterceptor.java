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
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) {

		log.info("The current request URL is {}.", request.getRequestURI());
		log.info("The current request HTTP method is {}.", request.getMethod());

		if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
			log.info("The Options method responds with a 200 code.");
			return true;
		}

		String header = request.getHeader(ACCESS_TOKEN);
		Long memberId = parsingJwtToken(header);
		request.setAttribute(MEMBER_ID, memberId);

		return true;
	}

	private Long parsingJwtToken(String header) {
		if (header.isBlank() || !header.startsWith(BEARER)) {
			throw new JwtTokenException(ErrorDetail.EMPTY_BEARER_JWT_TOKEN);
		}

		Claims claims = null;

		try {
			String jwtToken = header.replaceFirst(BEARER, Strings.EMPTY).trim();
			log.info("JwtToken is {}", jwtToken);
			claims = jwtTokenProvider.verifyToken(jwtToken);
		} catch (ExpiredJwtException e) {
			throw new JwtTokenException(ErrorDetail.EXPIRED_JWT_TOKEN);
		} catch (JwtException e) {
			e.printStackTrace();
			throw new JwtTokenException(ErrorDetail.NOT_VALID_JWT_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Long.valueOf(claims.getAudience());
	}
}
