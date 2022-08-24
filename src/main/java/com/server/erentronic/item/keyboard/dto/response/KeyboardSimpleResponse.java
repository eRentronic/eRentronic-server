package com.server.erentronic.item.keyboard.dto.response;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.product.dto.DiscountInfoResponse;
import com.server.erentronic.item.product.dto.ProductResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardSimpleResponse {

	private final ProductResponse product;

	private final DiscountInfoResponse discountInfo;

	private final KeyboardVendorResponse vendor;

	public static KeyboardSimpleResponse of(Keyboard keyboard) {
		return new KeyboardSimpleResponse(
			ProductResponse.from(keyboard),
			DiscountInfoResponse.from(keyboard),
			KeyboardVendorResponse.from(keyboard.getVendor()));
	}
}
