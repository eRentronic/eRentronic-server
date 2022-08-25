package com.server.erentronic.common.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Message {

	ORDER_SUCCESS_MESSAGE("주문이 완료되었습니다."),

	PRODUCT_CREATED_MESSAGE("상품이 등록되었습니다."),
	PRODUCT_DELETED_MESSAGE("상품이 삭제되었습니다."),
	PRODUCT_PATCHED_MESSAGE("상품이 수정되었습니다.");

	final String message;
}
