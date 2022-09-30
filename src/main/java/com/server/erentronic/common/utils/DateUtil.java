package com.server.erentronic.common.utils;

import com.server.erentronic.common.exception.InvalidInputException;
import com.server.erentronic.common.message.ErrorDetail;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

	public static void validatePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())
			|| startDateTime.toLocalDate().isAfter(endDateTime.toLocalDate())) {
			throw new InvalidInputException(ErrorDetail.INVALID_PERIOD);
		}
	}
}
