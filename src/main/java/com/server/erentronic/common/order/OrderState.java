package com.server.erentronic.common.order;

public enum OrderState {

	// 상품 준비중, 배송 준비중, 배송 중, 배송 완료, 주문 취소
	PREPARING_PRODUCT, PREPARING_SHIPPING, SHIPPING, COMPLETED, CANCELED
}
