package com.server.erentronic.common.order.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RentalRequest {

	@Positive
	private Long productId;

	@Positive
	private Integer quantity;

	@Positive
	private Integer productTotalPrice;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime startDateTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime endDateTime;
}
