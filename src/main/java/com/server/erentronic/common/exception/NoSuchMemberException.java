package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class NoSuchMemberException extends ApplicationException {

	public NoSuchMemberException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public NoSuchMemberException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
