package com.server.erentronic.common.exception;

import com.server.erentronic.common.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(
		MethodArgumentNotValidException e) {

		return ResponseEntity.badRequest().body(ErrorResponse.of(e));
	}

	@ExceptionHandler(ApplicationException.class)
	private ResponseEntity<ErrorResponse> applicationExceptionHandler(ApplicationException e) {
		return ResponseEntity.status(e.getErrorDetail().getStatus()).body(ErrorResponse.of(e.getErrorDetail()));
	}
}
