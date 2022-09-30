package com.server.erentronic.common.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorDetail {

	INVALID_INPUT_VALUE("입력 값이 올바르지 않습니다.", 400),
	INVALID_PERIOD("시작일이 종료일 이전이어야합니다.", 400),

	NOT_EQUALS_REAL_PRICE("주문한 해당 상품에 대한 총 가격이 일치하지 않습니다.", 400),
	NOT_EQUALS_REAL_TOTAL_PRICE("주문한 모든 상품에 대한 총 가격이 일치하지 않습니다.", 400),

	NO_SUCH_PRODUCT("해당 상품이 존재하지 않습니다.", 400),
	NO_SUCH_VENDOR("해당 공급사가 존재하지 않습니다.", 400),
	NO_SUCH_CONNECTION("해당 접점 방식이 존재하지 않습니다.", 400),
	NO_SUCH_LAYOUT("해당 배열이 존재하지 않습니다.", 400),
	NO_SUCH_ORDER_SHEET("해당 주문이 존재하지 않습니다.", 400),

	NO_STOCK_PRODUCT("해당 상품의 재고가 없습니다.", 202),

	//auth 관련
	NO_SUCH_MEMBER("해당 회원이 존재하지 않습니다.", 400),
	DUPLICATED_EMAIL("해당 email 의 회원이 이미 존재합니다.", 400),
	NO_SUCH_MEMBER_WITH_EMAIL("해당 email 의 회원이 존재하지 않습니다.", 400),
	NOT_MATCH_PASSWORD("비밀번호가 올바르지 않습니다.", 400),
	NOT_MATCH_ORDER_SHEET_MEMBER("해당 주문서의 회원과 로그인한 회원이 일치하지 않습니다.", 400),
	EMPTY_BEARER_JWT_TOKEN("bearer JWT 토큰이 존재하지 않습니다.", 400),
	EXPIRED_JWT_TOKEN("기간이 만료된 토큰입니다.", 400),
	NOT_VALID_JWT_TOKEN("유효하지 않은 토큰입니다.", 400);

	private final String message;
	private final int status;
}
