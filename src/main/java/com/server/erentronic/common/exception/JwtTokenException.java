package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class JwtTokenException extends ApplicationException {

	public JwtTokenException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public JwtTokenException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
