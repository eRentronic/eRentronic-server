package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.product.ProductImage;
import com.server.erentronic.item.product.ProductInfoImage;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.product.dto.DiscountInfoResponse;
import com.server.erentronic.item.product.dto.ImageResponse;
import com.server.erentronic.item.product.dto.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardDetailResponse {

	private final ProductResponse product;

	private final Long keyboardId;

	private final KeyboardVendorResponse vendor;

	private final KeyboardConnectionResponse connection;

	private final List<KeyboardSwitchResponse> keyboardSwitches;

	private final KeyboardLayoutResponse layout;

	private final List<ImageResponse> keyboardImages;

	private final List<ImageResponse> keyboardInfoImages;

	private final DiscountInfoResponse discountInfoResponse;

	public static KeyboardDetailResponse from(Keyboard keyboard) {
		return new KeyboardDetailResponse(
			ProductResponse.from(keyboard),

			keyboard.getId(),

			KeyboardVendorResponse.from(keyboard.getVendor()),

			KeyboardConnectionResponse.from(keyboard.getConnection()),

			keyboard.getKeyboardSwitches().stream()
				.map(KeyboardSwitch::getSwitch)
				.map(KeyboardSwitchResponse::from)
				.collect(Collectors.toList()),

			KeyboardLayoutResponse.from(keyboard.getLayout()),

			keyboard.getProductImages().stream()
				.map(ProductImage::getImage)
				.map(ImageResponse::from)
				.collect(Collectors.toList()),

			keyboard.getProductInfoImages().stream()
				.map(ProductInfoImage::getImage)
				.map(ImageResponse::from)
				.collect(Collectors.toList()),

			DiscountInfoResponse.from(keyboard)
		);
	}
}
