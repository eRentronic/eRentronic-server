package com.server.erentronic.common.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Message {

	PRODUCT_CREATED_MESSAGE("상품이 등록되었습니다.");

	final String message;
}
