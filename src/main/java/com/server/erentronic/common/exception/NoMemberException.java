package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class NoMemberException extends ApplicationException {

	public NoMemberException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public NoMemberException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
