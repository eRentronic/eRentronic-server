package com.server.erentronic.common.utils;

import com.server.erentronic.common.exception.InvalidInputException;
import com.server.erentronic.common.message.ErrorDetail;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DateUtil {

	public static void validatePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {

		log.info("startDateTime: {}", startDateTime.toString());
		log.info("endDateTime: {}", endDateTime.toString());
		if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())
			|| startDateTime.toLocalDate().isAfter(endDateTime.toLocalDate())) {
			throw new InvalidInputException(ErrorDetail.INVALID_PERIOD);
		}
	}
}
