package com.server.erentronic.item.keyboard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.erentronic.item.product.type.Connection;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardConnectionResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -7412666456034490455L;

	private final Long id;

	private final String name;

	public static KeyboardConnectionResponse from(Connection connection) {
		return new KeyboardConnectionResponse(connection.getId(), connection.getConnectionType().getName());
	}
}
