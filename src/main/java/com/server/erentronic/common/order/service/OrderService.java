package com.server.erentronic.common.order.service;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.MemberRepository;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.message.Message;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.order.OrderSheet;
import com.server.erentronic.common.order.dto.OrderRequest;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.purchace.Purchase;
import com.server.erentronic.common.order.repository.OrderSheetRepository;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductUnit;
import com.server.erentronic.item.product.UnitState;
import com.server.erentronic.item.product.repository.ProductRepository;
import com.server.erentronic.item.product.repository.ProductUnitRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	private final OrderSheetRepository orderSheetRepository;
	private final ProductRepository productRepository;
	private final ProductUnitRepository productUnitRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public CUDResponse purchase(Member loginMember, OrderSheetRequest orderSheetRequests) {

		//todo 로그인 기능 구현 후 제거해야 함
		loginMember = memberRepository.findById(1L).get();

		List<OrderRequest> orderRequests = orderSheetRequests.getOrders();

		Integer totalPrice = 0;
		List<Order> purchases = new ArrayList<>();

		for (OrderRequest orderRequest : orderRequests) {
			Product product = productRepository.findById(orderRequest.getProductId())
				.orElseThrow(RuntimeException::new);

			Purchase purchase = Purchase.makePurchase(product, orderRequest.getQuantity(),
				orderRequest.getProductTotalPrice());

			List<ProductUnit> units = productUnitRepository.findAllByProductAndState(product, UnitState.SALE)
				.subList(0, orderRequest.getQuantity());

			units.forEach(productUnit -> productUnit.changeState(UnitState.SOLD_OUT));
			purchase.assignUnits(units);

			purchases.add(purchase);

			totalPrice += product.getPrice() * orderRequest.getQuantity();
		}

		validateRequestPrice(orderSheetRequests, totalPrice);

		OrderSheet orderSheet = OrderSheet.makeOrderSheet(purchases, loginMember,
			orderSheetRequests.getAddress(), totalPrice);

		orderSheetRepository.save(orderSheet);

		return CUDResponse.of(orderSheet.getId(), Message.ORDER_SUCCESS_MESSAGE);
	}

	private void validateRequestPrice(OrderSheetRequest orderSheetRequests, Integer totalPrice) {
		if (totalPrice.compareTo(orderSheetRequests.getTotalPrice()) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_TOTAL_PRICE);
		}
	}

	public CUDResponse rent(Member loginMember, OrderSheetRequest orderSheetRequest) {
		return null;
	}
}
