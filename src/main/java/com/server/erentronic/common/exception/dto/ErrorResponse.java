package com.server.erentronic.common.exception.dto;

import com.server.erentronic.common.message.ErrorDetail;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponse {

	private final String message;

	private final List<FieldError> errors = new ArrayList<>();

	private ErrorResponse(ErrorDetail errorDetail, List<FieldError> errors) {
		this.message = errorDetail.getMessage();
		this.errors.addAll(errors);
	}

	public static ErrorResponse of(ErrorDetail errorDetail) {
		return new ErrorResponse(errorDetail, Collections.emptyList());
	}

	// For ConstraintViolationException
	public static ErrorResponse of(ErrorDetail errorDetail, BindingResult bindingResult) {
		return new ErrorResponse(errorDetail, FieldError.of(bindingResult));
	}

	// For MethodArgumentNotValidException
	public static ErrorResponse of(MethodArgumentNotValidException e) {
		List<FieldError> errors = ErrorResponse.FieldError.of(e.getBindingResult());
		return new ErrorResponse(ErrorDetail.INVALID_INPUT_VALUE, errors);
	}

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	@Getter
	public static class FieldError {

		private final String field;

		private final String value;

		private final String reason;

		private FieldError(org.springframework.validation.FieldError fieldError) {
			this.field = fieldError.getField();
			this.value = fieldError.getRejectedValue() == null ? "" : String.valueOf(fieldError.getRejectedValue());
			this.reason = fieldError.getDefaultMessage();
		}

		private static List<FieldError> of(BindingResult bindingResult) {
			List<org.springframework.validation.FieldError> fieldErrors = bindingResult
				.getFieldErrors();

			return fieldErrors.stream()
				.map(FieldError::new)
				.collect(Collectors.toList());
		}
	}
}
