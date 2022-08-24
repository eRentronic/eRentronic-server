package com.server.erentronic.common.order.service;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

	public CUDResponse makePurchase(Member loginMember, OrderSheetRequest orderSheetRequests) {
		// 가격도 받고 -> db 꺼내서 계산한 값이랑 비교 한번 하는건?
		// orderSheetRequest에 있는 리스트<OrderRequest>를 통해 구매 리스트를 만듬
		// 구매(purchase) 테이블들을 포함한 ordersheet 1개 만들고 저장

		orderSheetRequests.getOrders();
		return null;
	}
}
