package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class InvalidInputException extends ApplicationException {

	public InvalidInputException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public InvalidInputException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
