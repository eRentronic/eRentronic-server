package com.server.erentronic.common.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorDetail {
	INVALID_INPUT_VALUE("입력 값이 올바르지 않습니다.", 400),

	NO_SUCH_PRODUCT("해당 상품이 존재하지 않습니다.", 400),
	NO_SUCH_VENDOR("해당 공급사가 존재하지 않습니다.", 400),
	NO_SUCH_CONNECTION("해당 접점 방식이 존재하지 않습니다.", 400),
	NO_SUCH_LAYOUT("해당 배열이 존재하지 않습니다.", 400),

	NO_STOCK_PRODUCT("해당 상품의 재고가 없습니다.", 202);

	private final String message;
	private final int status;
}
