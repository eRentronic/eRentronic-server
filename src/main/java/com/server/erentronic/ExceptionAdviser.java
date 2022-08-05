package com.server.erentronic;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler(ConstraintViolationException.class)
	private List<String> test(ConstraintViolationException e) {
		return e.getConstraintViolations().stream()
			.map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private String test2(MethodArgumentNotValidException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		for (ObjectError allError : allErrors) {
			System.out.println(allError.getDefaultMessage());
		}
		return e.getMessage();
	}
}
