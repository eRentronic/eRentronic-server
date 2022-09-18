package com.server.erentronic.item.keyboard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.erentronic.item.keyboard.type.Layout;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardLayoutResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -9207850282365601550L;

	private final Long id;

	private final String name;

	public static KeyboardLayoutResponse from(Layout layout) {
		return new KeyboardLayoutResponse(layout.getId(), layout.getLayoutType().getName());
	}
}
