package com.server.erentronic.auth.jwt;

import com.server.erentronic.common.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

	private final String issuer;
	private final String secretKey;
	private final Long accessTokenExpirationHour;

	public JwtTokenProvider(JwtProperties properties) {
		this.issuer = properties.getIssuer();
		this.secretKey = properties.getSecretKey();
		this.accessTokenExpirationHour = properties.getAccessTokenExpirationHour();
	}

	public String makeJwtAccessToken(Member member) {
		return Jwts.builder()
			.setAudience(member.getId().toString())
			.setIssuer(issuer)
			.setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
			.setExpiration(Timestamp.valueOf(LocalDateTime.now().plusHours(accessTokenExpirationHour)))
			.signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
			.compact();

	}

	public Claims verifyToken(String jwtToken) {
		return Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
			.build()
			.parseClaimsJws(jwtToken)
			.getBody();
	}
}
