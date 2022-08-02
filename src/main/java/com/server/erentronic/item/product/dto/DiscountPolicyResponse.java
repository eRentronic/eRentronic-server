package com.server.erentronic.item.product.dto;

import com.server.erentronic.common.discountpolicy.DiscountPolicy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DiscountPolicyResponse {

	private final Long id;

	private final String title;

	private final Double rate;

	public static DiscountPolicyResponse from(DiscountPolicy discountPolicy) {
		return new DiscountPolicyResponse(
			discountPolicy.getId(),
			discountPolicy.getTitle(),
			discountPolicy.getRate()
		);
	}
}
