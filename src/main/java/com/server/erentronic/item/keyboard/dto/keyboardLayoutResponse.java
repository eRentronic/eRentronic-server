package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.type.Layout;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class keyboardLayoutResponse {

	private final Long id;

	private final String name;

	public static keyboardLayoutResponse from(Layout layout) {
		return new keyboardLayoutResponse(layout.getId(), layout.getLayoutType().getName());
	}
}
