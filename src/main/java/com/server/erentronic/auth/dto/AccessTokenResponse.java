package com.server.erentronic.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccessTokenResponse {

	private final String accessToken;

	public static AccessTokenResponse of(String accessToken) {
		return new AccessTokenResponse(accessToken);
	}
}
