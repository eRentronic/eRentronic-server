package com.server.erentronic.item.keyboard.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardFilterResponse {

	private final List<KeyboardVendorResponse> vendors;

	private final List<KeyboardConnectionResponse> keyboardConnections;

	private final List<KeyboardSwitchResponse> switches;

	private final List<KeyboardLayoutResponse> layouts;

	public static KeyboardFilterResponse of (
		List<KeyboardVendorResponse> vendors,
		List<KeyboardConnectionResponse> keyboardConnections,
		List<KeyboardSwitchResponse> switches,
		List<KeyboardLayoutResponse> layouts) {

		return new KeyboardFilterResponse(vendors, keyboardConnections, switches, layouts);
	}
}
