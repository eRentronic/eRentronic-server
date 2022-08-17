package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

	private final ErrorDetail errorDetail;

	public ApplicationException(ErrorDetail errorDetail) {
		super(errorDetail.getMessage());
		this.errorDetail = errorDetail;
	}

	public ApplicationException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail.getMessage(), cause);
		this.errorDetail = errorDetail;
	}
}
