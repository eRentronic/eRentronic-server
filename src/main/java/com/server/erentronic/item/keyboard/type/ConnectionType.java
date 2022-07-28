package com.server.erentronic.item.keyboard.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ConnectionType {

	CABLE("유선"),
	WIRELESS("무선"),
	BLUETOOTH("블루투스");

	private final String name;
}
