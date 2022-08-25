package com.server.erentronic.item.keyboard.dto.response;

import com.server.erentronic.item.keyboard.type.Switch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardSwitchResponse {

	private final Long id;

	private final String name;

	public static KeyboardSwitchResponse from(Switch aSwitch) {
		return new KeyboardSwitchResponse(aSwitch.getId(), aSwitch.getSwitchType().getName());
	}
}