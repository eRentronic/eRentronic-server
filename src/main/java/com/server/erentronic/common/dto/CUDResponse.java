package com.server.erentronic.common.dto;

import com.server.erentronic.common.message.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CUDResponse {

	private final Long id;

	private final String message;

	public static CUDResponse of(Long id, Message message) {
		return new CUDResponse(id, message.getMessage());
	}
}
