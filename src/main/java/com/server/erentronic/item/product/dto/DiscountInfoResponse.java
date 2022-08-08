package com.server.erentronic.item.product.dto;

import com.server.erentronic.common.discountpolicy.DiscountPolicy;
import com.server.erentronic.common.utils.SalePriceCalculator;
import com.server.erentronic.item.keyboard.ProductDiscountPolicy;
import com.server.erentronic.item.product.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DiscountInfoResponse {

	private final List<ProductDiscountResponse> discounts;

	private final Integer salePrice;

	private final Integer saleRentalPrice;

	public static DiscountInfoResponse from(Product product) {
		double saleRate = product.getDiscountPolicies().stream()
			.map(ProductDiscountPolicy::getDiscountPolicy)
			.mapToDouble(DiscountPolicy::getRate)
			.sum();

		return new DiscountInfoResponse(
			product.getDiscountPolicies().stream()
				.map(ProductDiscountResponse::from)
				.collect(Collectors.toList()),
			SalePriceCalculator.calculate(product.getPrice(), saleRate),
			SalePriceCalculator.calculate(product.getRentalPrice(), saleRate)
		);
	}
}
