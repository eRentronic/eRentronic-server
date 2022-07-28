package com.server.erentronic.item.keyboard.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LayoutType {

	FULL("기본"),
	TEN_KEY_LESS("텐키리스"),
	SEVENTY_FIVE_PERCENT("75%"),
	SIXTY_FIVE_PERCENT("65%");

	private final String name;
}
