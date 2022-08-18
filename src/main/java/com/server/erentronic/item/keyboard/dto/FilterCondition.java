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

	private final String keyword;

	private final Boolean rentable;

	private final Boolean purchasable;
}
