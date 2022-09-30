package com.server.erentronic.common.order.controller;

import com.server.erentronic.auth.annotation.LoginId;
import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.order.dto.OrderHistoryResponses;
import com.server.erentronic.common.order.dto.OrderSearchRequest;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@GetMapping
	public List<OrderHistoryResponses> getOrderHistory(@LoginId Long memberId, @ModelAttribute OrderSearchRequest orderSearchRequest) {
		return orderService.getOrderHistory(memberId, orderSearchRequest);
	}

	@PostMapping
	public CUDResponse order(@LoginId Long memberId, @RequestBody @Valid OrderSheetRequest orderSheetRequest) {

		return orderService.order(memberId, orderSheetRequest);
	}

	@PatchMapping("/{orderSheetId}")
	public CUDResponse cancelOrderSheet(@LoginId Long memberId, @PathVariable Long orderSheetId) {

		return orderService.cancelOrderSheet(memberId, orderSheetId);
	}
}
