package com.server.erentronic.item.keyboard.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ConnectionType {

	CABLE("유선"),
	WIRELESS("무선"),
	BLUETOOTH("블루투스");

	private final String name;
}
