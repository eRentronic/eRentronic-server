package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class AuthException extends ApplicationException {

	public AuthException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public AuthException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
