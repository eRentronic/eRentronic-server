package com.server.erentronic.common.order.controller;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
	public CUDResponse purchase(Member loginMember, @RequestBody @Valid OrderSheetRequest orderSheetRequest) {

		return orderService.purchase(loginMember, orderSheetRequest);
	}

	@PostMapping("/rental")
	public CUDResponse rent(Member loginMember, @RequestBody @Valid OrderSheetRequest orderSheetRequest) {

		return orderService.rent(loginMember, orderSheetRequest);
	}
}
