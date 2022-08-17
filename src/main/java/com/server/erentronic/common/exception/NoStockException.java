package com.server.erentronic.common.exception;

import com.server.erentronic.common.message.ErrorDetail;

public class NoStockException extends ApplicationException {

	public NoStockException(ErrorDetail errorDetail) {
		super(errorDetail);
	}

	public NoStockException(ErrorDetail errorDetail, Throwable cause) {
		super(errorDetail, cause);
	}
}
