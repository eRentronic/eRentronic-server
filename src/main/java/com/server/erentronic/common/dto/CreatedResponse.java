package com.server.erentronic.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreatedResponse {

	private final Long id;

	private final String message;

	public static CreatedResponse of(Long id, String message) {
		return new CreatedResponse(id, message);
	}
}
