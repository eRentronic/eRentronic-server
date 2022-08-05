package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.product.type.Vendor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardVendorResponse {

	private final Long id;

	private final String name;

	public static KeyboardVendorResponse from(Vendor vendor) {
		return new KeyboardVendorResponse(vendor.getId(), vendor.getVendorType().getName());
	}
}
