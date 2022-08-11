package com.server.erentronic.common.exception;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler(ConstraintViolationException.class)
	private ErrorResponse constraintViolationExceptionHandler(ConstraintViolationException e) {
		List<String> messages = e.getConstraintViolations().stream()
			.map(ConstraintViolation::getMessage)
			.collect(Collectors.toList());

		return ErrorResponse.from(messages);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ErrorResponse methodArgumentNotValidExceptionHandler(
		MethodArgumentNotValidException e) {

		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		List<String> messages = allErrors.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.toList());

		return ErrorResponse.from(messages);
	}

	@RequiredArgsConstructor
	@Getter
	static class ErrorResponse<T> {

		private final T messages;

		public static <T> ErrorResponse<T> from(T messages) {
			return new ErrorResponse<>(messages);
		}
	}
}
