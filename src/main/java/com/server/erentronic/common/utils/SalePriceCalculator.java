package com.server.erentronic.common.utils;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalePriceCalculator {

	public static int calculate(Integer originalPrice, Double saleRate) {
		Objects.requireNonNull(originalPrice);
		Objects.requireNonNull(saleRate);

		return (int) (originalPrice * (1 - saleRate));
	}
}
