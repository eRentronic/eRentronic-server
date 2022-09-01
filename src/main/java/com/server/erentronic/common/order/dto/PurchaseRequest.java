package com.server.erentronic.common.order.dto;

import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PurchaseRequest {

	@Positive
	private Long productId;

	@Positive
	private Integer quantity;

	@Positive
	private Integer productTotalPrice;
}
