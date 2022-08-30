package com.server.erentronic.item.product.dto;

import com.server.erentronic.item.product.ProductDiscountPolicy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductDiscountResponse {

	private final Long id;

	private final String title;

	private final String saleRate;

	public static ProductDiscountResponse from(ProductDiscountPolicy productDiscountPolicy) {
		return new ProductDiscountResponse(productDiscountPolicy.getDiscountPolicy().getId(),
			productDiscountPolicy.getDiscountPolicy().getTitle(),
			String.valueOf(productDiscountPolicy.getDiscountPolicy().getRate() * 100)
		);
	}
}
