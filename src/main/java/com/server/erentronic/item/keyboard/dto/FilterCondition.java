package com.server.erentronic.item.keyboard.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FilterCondition {

	private final List<Long> connectionIds;

	private final List<Long> vendorIds;

	private final List<Long> layoutIds;

	private final List<Long> switchIds;

	// 1이면 구매가능, 2면 대여가능
	private final List<Long> availableServices;
}
