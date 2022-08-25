package com.server.erentronic.item.keyboard.dto.response;

import com.server.erentronic.item.product.type.Connection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardConnectionResponse {

	private final Long id;

	private final String name;

	public static KeyboardConnectionResponse from(Connection connection) {
		return new KeyboardConnectionResponse(connection.getId(), connection.getConnectionType().getName());
	}
}