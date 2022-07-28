package com.server.erentronic.item.keyboard.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KeyboardFilterResponse {

	private final List<KeyboardVendorResponse> vendors;

	private final List<KeyboardConnectionResponse> keyboardConnections;

	private final List<KeyboardSwitchResponse> switches;

	private final List<keyboardLayoutResponse> layouts;
}
