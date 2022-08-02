package com.server.erentronic.item.product.dto;

import com.server.erentronic.common.discountpolicy.DiscountPolicy;
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

		// todo 유틸클래스? 그냥 해도 상관 없을 것 같기는 하고.
		return new DiscountInfoResponse(
			product.getDiscountPolicies().stream()
				.map(ProductDiscountResponse::from)
				.collect(Collectors.toList()),
			(int) (product.getPrice() * (1 - saleRate)),
			(int) (product.getRentalPrice() * (1 - saleRate))
		);
	}
}
