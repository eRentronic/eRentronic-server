package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class NotMatchException extends ApplicationException {

	public NotMatchException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public NotMatchException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}

}
