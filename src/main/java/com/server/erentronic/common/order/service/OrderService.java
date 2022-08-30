package com.server.erentronic.common.order.service;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.exception.NoSuchItemException;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.MemberRepository;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.message.Message;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.order.OrderSheet;
import com.server.erentronic.common.order.OrderState;
import com.server.erentronic.common.order.dto.OrderRequest;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.purchace.Purchase;
import com.server.erentronic.common.order.rental.Rental;
import com.server.erentronic.common.order.repository.OrderSheetRepository;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductState;
import com.server.erentronic.item.product.ProductUnit;
import com.server.erentronic.item.product.UnitState;
import com.server.erentronic.item.product.repository.ProductRepository;
import com.server.erentronic.item.product.repository.ProductUnitRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
	public CUDResponse makePurchase(Member loginMember, OrderSheetRequest orderSheetRequests) {

		//todo 로그인 기능 구현 후 제거해야 함
		loginMember = memberRepository.findById(1L).get();

		List<OrderRequest> orderRequests = orderSheetRequests.getOrders();

		Integer totalPrice = 0;
		List<Order> purchases = new ArrayList<>();

		for (OrderRequest orderRequest : orderRequests) {
			Product product = productRepository.findById(orderRequest.getProductId())
				.orElseThrow(RuntimeException::new);

			log.debug("orderRequestQuantity: {}, orderRequestTotalPrice: {}, productPrice: {}",
				orderRequest.getQuantity(), orderRequest.getProductTotalPrice(),
				product.getPrice());

			Purchase purchase = Purchase.makePurchase(product, orderRequest.getQuantity(),
				orderRequest.getProductTotalPrice());

			List<ProductUnit> units = productUnitRepository.findAllByProductAndState(
				product, UnitState.SALE).subList(0, orderRequest.getQuantity());

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

	@Transactional
	public CUDResponse cancelOrderSheet(Member loginMember, Long orderSheetId) {
		//todo loginMember와 orderSheet의 멤버가 동일한 사람인지 판별해야 함
		// 현재는 상품 구매에 대해서만 취소 중
		// 상품 렌탈 에 대해서도 취소할 수 있도록 추후에 변경해야 함 instanceOf Rental 로 List<Order>를 List<Rental>

		OrderSheet prevOrderSheet = orderSheetRepository.findById(orderSheetId)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_ORDER_SHEET));

		List<Purchase> purchases = prevOrderSheet.getOrders()
			.stream().map(Purchase.class::cast)
			.collect(Collectors.toList());

		purchases.forEach(Purchase::cancel);
		prevOrderSheet.changeState(OrderState.CANCELED);

		return CUDResponse.of(orderSheetId, Message.ORDER_CANCEL_MESSAGE);
	}

	private void validateRequestPrice(OrderSheetRequest orderSheetRequests, Integer totalPrice) {
		if (totalPrice.compareTo(orderSheetRequests.getTotalPrice()) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_TOTAL_PRICE);
		}
	}
}
