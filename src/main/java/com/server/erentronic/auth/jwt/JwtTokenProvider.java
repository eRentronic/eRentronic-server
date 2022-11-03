package com.server.erentronic.auth.jwt;

import com.server.erentronic.common.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

	private final String issuer;
	private final SecretKey secretKey;
	private final Long accessTokenExpirationHour;

	public JwtTokenProvider(JwtProperties properties) {
		this.issuer = properties.getIssuer();
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecretKey()));
		this.accessTokenExpirationHour = properties.getAccessTokenExpirationHour();
	}

	public String makeJwtAccessToken(Member member) {
		LocalDateTime now = LocalDateTime.now();
		return Jwts.builder()
			.setAudience(String.valueOf(member.getId()))
			.setIssuer(issuer)
			.setIssuedAt(Timestamp.valueOf(now))
			.setExpiration(Timestamp.valueOf(now.plusHours(accessTokenExpirationHour)))
			.signWith(secretKey)
			.compact();
	}

	public Claims verifyToken(String jwtToken) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(jwtToken)
			.getBody();
	}
}
