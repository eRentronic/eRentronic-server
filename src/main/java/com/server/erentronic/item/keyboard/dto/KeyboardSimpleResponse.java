package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardDiscountPolicy;
import com.server.erentronic.item.keyboard.KeyboardImage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardSimpleResponse {

	private final Long id;

	private final String title;

	private final String content;

	private final String imageUrl;

	private final Integer price;

	private final Integer rentalPrice;

	private final Boolean rentable;

	private final Boolean like;

	private final List<KeyboardDiscountResponse> discounts;

	private final Integer salePrice;

	private final Integer saleRentalPrice;

	private final KeyboardVendorResponse vendor;

	//todo 나중에 salePrice, saleRentalPrice 가격 구하는 로직 구현해야 함
	public static KeyboardSimpleResponse of(Keyboard keyboard) {
		return new KeyboardSimpleResponse(keyboard.getId(),
			keyboard.getTitle(),
			keyboard.getContent(),
			keyboard.getKeyboardImages().get(0).getImage().getImageUrl(),
			keyboard.getPrice(), keyboard.getRentalPrice(),
			keyboard.getRentable(),
			null,
			keyboard.getDiscountPolicies().stream()
				.map(KeyboardDiscountResponse::from)
				.collect(Collectors.toList()),
			keyboard.getPrice(),
			keyboard.getRentalPrice(),
			KeyboardVendorResponse.from(keyboard.getVendor()));
	}
}
