package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.type.Connection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KeyboardConnectionResponse {

	private final Long id;

	private final String name;

	public static KeyboardConnectionResponse from(Connection connection) {
		return new KeyboardConnectionResponse(connection.getId(), connection.getConnectionType().getName());
	}
}
