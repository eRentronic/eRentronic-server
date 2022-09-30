package com.server.erentronic.common.order.dto;

import com.server.erentronic.common.address.Address;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.order.OrderSheet;
import com.server.erentronic.common.order.OrderState;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderHistoryResponses {

	private final Long orderSheetId;

	private final LocalDateTime createAt;

	private final Address address;

	private final Integer totalPrice;

	private final OrderState state;

	private final List<OrderHistoryResponse> orderHistoryResponses;

	public static OrderHistoryResponses from(OrderSheet orderSheets, String dtype) {
		List<Order> orders = orderSheets.getOrders();
		List<OrderHistoryResponse> orderHistoryResponses = orders.stream()
			.filter(order -> order.isOrderType(dtype))
			.map(OrderHistoryResponse::from)
			.collect(Collectors.toList());

		return new OrderHistoryResponses(
			orderSheets.getId(),
			orderSheets.getCreatedAt(),
			orderSheets.getAddress(),
			orderSheets.getTotalPrice(),
			orderSheets.getState(),
			orderHistoryResponses);
	}
}
