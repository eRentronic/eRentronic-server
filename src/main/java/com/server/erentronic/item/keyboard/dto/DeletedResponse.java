package com.server.erentronic.item.keyboard.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DeletedResponse {

	private final Long id;

	public static DeletedResponse from(Long id) {
		return new DeletedResponse(id);
	}
}
