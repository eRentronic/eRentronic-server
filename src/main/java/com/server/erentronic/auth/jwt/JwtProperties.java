package com.server.erentronic.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@Getter
public class JwtProperties {

	private final String issuer;
	private final String secretKey;
	private final Long accessTokenExpirationHour;
}

