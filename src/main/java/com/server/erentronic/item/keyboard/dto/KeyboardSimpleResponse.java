package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.product.dto.DiscountInfoResponse;
import com.server.erentronic.item.product.dto.ProductDiscountResponse;
import com.server.erentronic.item.product.dto.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardSimpleResponse {

	private final ProductResponse product;

	private final DiscountInfoResponse discountInfo;

	private final KeyboardVendorResponse vendor;

	//todo 나중에 salePrice, saleRentalPrice 가격 구하는 로직 구현해야 함
	public static KeyboardSimpleResponse of(Keyboard keyboard) {
		return new KeyboardSimpleResponse(
			ProductResponse.from(keyboard),
			DiscountInfoResponse.from(keyboard),
			KeyboardVendorResponse.from(keyboard.getVendor()));
	}
}
