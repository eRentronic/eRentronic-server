package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.KeyboardDiscountPolicy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardDiscountResponse {

	private final Long id;

	private final String title;

	public static KeyboardDiscountResponse from(KeyboardDiscountPolicy keyboardDiscountPolicy) {
		return new KeyboardDiscountResponse(keyboardDiscountPolicy.getDiscountPolicy().getId(), keyboardDiscountPolicy.getDiscountPolicy().getTitle());
	}
}
