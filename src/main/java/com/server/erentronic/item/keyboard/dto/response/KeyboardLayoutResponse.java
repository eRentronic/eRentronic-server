package com.server.erentronic.item.keyboard.dto.response;

import com.server.erentronic.item.keyboard.type.Layout;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardLayoutResponse {

	private final Long id;

	private final String name;

	public static KeyboardLayoutResponse from(Layout layout) {
		return new KeyboardLayoutResponse(layout.getId(), layout.getLayoutType().getName());
	}
}
