package com.server.erentronic.common.order.dto;

import java.time.LocalDate;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
public class OrderSearchRequest {

	private String dtype;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate endDate;

	private OrderSearchRequest(String dtype, LocalDate startDate, LocalDate endDate) {
		this.dtype = dtype;
		this.startDate = startDate == null ? LocalDate.now() : startDate;
		this.endDate = endDate == null ? LocalDate.now().minusMonths(3L) : endDate;
	}
}
