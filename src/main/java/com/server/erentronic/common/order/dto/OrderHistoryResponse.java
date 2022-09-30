package com.server.erentronic.common.order.dto;

import com.server.erentronic.common.order.Order;
import com.server.erentronic.item.product.dto.ProductResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderHistoryResponse {

	private final Long orderId;

	private final String dtype;

	private final ProductResponse product;

	private final Double totalDiscountRate;

	private final String discountDetail;

	private final Integer quantity;

	private final Integer price;

	private final Integer salePrice;

	public static OrderHistoryResponse from(Order order) {
		return new OrderHistoryResponse(order.getId(), order.getDtype(),
			ProductResponse.from(order.getProduct()), order.getTotalDiscountRate(),
			order.getDiscountDetail(), order.getQuantity(), order.getPrice(), order.getSalePrice());
	}
}
