package com.server.erentronic.common.order.controller;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/purchase")
	public CUDResponse makePurchase(Member loginMember, @RequestBody @Valid OrderSheetRequest orderSheetRequest) {

		return orderService.makePurchase(loginMember, orderSheetRequest);
	}

	@PatchMapping("/{orderSheetId}")
	public CUDResponse cancelOrderSheet(Member loginMember, @PathVariable Long orderSheetId) {

		return orderService.cancelOrderSheet(loginMember, orderSheetId);
	}
}
