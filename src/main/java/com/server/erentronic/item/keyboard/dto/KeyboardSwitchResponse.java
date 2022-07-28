package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.type.Switch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KeyboardSwitchResponse {

	private final Long id;

	private final String name;

	public static KeyboardSwitchResponse from(Switch aSwitch) {
		return new KeyboardSwitchResponse(aSwitch.getId(), aSwitch.getSwitchType().getName());
	}
}
