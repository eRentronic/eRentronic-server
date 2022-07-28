package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.type.Vendor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KeyboardVendorResponse {

	private final Long id;

	private final String name;
	public static KeyboardVendorResponse from(Vendor vendor) {
		return new KeyboardVendorResponse(vendor.getId(), vendor.getVendorType().getName());
	}
}
