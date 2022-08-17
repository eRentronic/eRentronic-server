package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class NoSuchItemException extends ApplicationException {

	public NoSuchItemException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public NoSuchItemException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
