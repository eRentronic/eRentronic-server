package com.server.erentronic.item.keyboard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.erentronic.item.keyboard.type.Switch;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardSwitchResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -8842179157884768791L;

	private final Long id;

	private final String name;

	public static KeyboardSwitchResponse from(Switch aSwitch) {
		return new KeyboardSwitchResponse(aSwitch.getId(), aSwitch.getSwitchType().getName());
	}
}
