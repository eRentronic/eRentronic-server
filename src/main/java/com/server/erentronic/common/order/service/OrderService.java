package com.server.erentronic.common.order.service;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.exception.NoStockException;
import com.server.erentronic.common.exception.NoSuchItemException;
import com.server.erentronic.common.exception.NoSuchMemberException;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.repository.MemberRepository;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.message.Message;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.order.OrderSheet;
import com.server.erentronic.common.order.OrderState;
import com.server.erentronic.common.order.dto.OrderHistoryResponses;
import com.server.erentronic.common.order.dto.OrderSearchRequest;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.dto.PurchaseRequest;
import com.server.erentronic.common.order.dto.RentalRequest;
import com.server.erentronic.common.order.purchace.Purchase;
import com.server.erentronic.common.order.rental.Rental;
import com.server.erentronic.common.order.repository.OrderSheetRepository;
import com.server.erentronic.common.utils.DateUtil;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductRentalUnit;
import com.server.erentronic.item.product.ProductUnit;
import com.server.erentronic.item.product.RentalUnitState;
import com.server.erentronic.item.product.UnitState;
import com.server.erentronic.item.product.repository.ProductRentalUnitRepository;
import com.server.erentronic.item.product.repository.ProductRepository;
import com.server.erentronic.item.product.repository.ProductUnitRepository;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

	private final OrderSheetRepository orderSheetRepository;
	private final ProductRepository productRepository;
	private final ProductUnitRepository productUnitRepository;
	private final ProductRentalUnitRepository productRentalUnitRepository;
	private final MemberRepository memberRepository;

	public List<OrderHistoryResponses> getOrderHistory(Long memberId,
		OrderSearchRequest orderSearchRequest) {

		LocalDateTime startDateTime = LocalDateTime.of(orderSearchRequest.getStartDate(),
			LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(orderSearchRequest.getEndDate(),
			LocalTime.MAX);

		DateUtil.validatePeriod(startDateTime, endDateTime);

		Member loginMember = memberRepository.findById(memberId)
			.orElseThrow(() -> new NoSuchMemberException(ErrorDetail.NO_SUCH_MEMBER));

		List<OrderSheet> orderSheets = orderSheetRepository.findWithinSpecificPeriod(
			loginMember, startDateTime, endDateTime);

		String dtype = orderSearchRequest.getDtype();

		return orderSheets.stream()
			.map(orderSheet -> OrderHistoryResponses.from(orderSheet, dtype))
			.collect(Collectors.toList());
	}

	@Transactional
	public CUDResponse order(Long memberId, OrderSheetRequest orderSheetRequest) {

		log.info(">>>>>>>>>>>>>> memberId: {}", memberId);

		Member loginMember = memberRepository.findById(memberId)
			.orElseThrow(() -> new NoSuchMemberException(ErrorDetail.NO_SUCH_MEMBER));

		List<Order> purchases = purchase(orderSheetRequest);
		List<Order> rentals = rent(orderSheetRequest);

		List<Order> orders = new ArrayList<>(purchases);
		orders.addAll(rentals);

		Integer totalPrice = orders.stream().mapToInt(Order::getSalePrice).sum();
		validateTotalPrice(orderSheetRequest.getTotalPrice(), totalPrice);

		OrderSheet orderSheet = OrderSheet.makeOrderSheet(orders, loginMember,
			orderSheetRequest.getAddress(), orderSheetRequest.getTotalPrice());

		orderSheetRepository.save(orderSheet);

		return CUDResponse.of(orderSheet.getId(), Message.ORDER_SUCCESS_MESSAGE);
	}

	private List<Order> purchase(OrderSheetRequest orderSheetRequest) {

		List<PurchaseRequest> purchaseRequests = orderSheetRequest.getPurchases();

		List<Order> purchases = new ArrayList<>();

		for (PurchaseRequest purchaseRequest : purchaseRequests) {
			Product product = productRepository.findById(purchaseRequest.getProductId())
				.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));

			purchaseProduct(purchases, purchaseRequest, product);
		}

		return purchases;
	}

	private void purchaseProduct(List<Order> purchases, PurchaseRequest purchaseRequest,
		Product product) {

		Purchase purchase = Purchase.makePurchase(product, purchaseRequest.getQuantity(),
			purchaseRequest.getProductTotalPrice());

		List<ProductUnit> units = productUnitRepository.findAllByProductAndState(product,
			UnitState.SALE);

		if (units.size() < purchaseRequest.getQuantity()) {
			throw new NoStockException(ErrorDetail.NO_STOCK_PRODUCT);
		}

		units = units.subList(0, purchaseRequest.getQuantity());

		units.forEach(productUnit -> productUnit.changeState(UnitState.SOLD_OUT));
		purchase.assignUnits(units);

		purchases.add(purchase);
	}

	private List<Order> rent(OrderSheetRequest orderSheetRequest) {

		//todo
		// 렌탈도 동시성 나중에 생각
		// 렌탈은 반납 일정 관리를 해야하기 때문에 새로운 테이블에 저장해야 함
		// 새로운 테이블은 OrderSheet fk, 나간 상품 Unit fk, 대여일, 반납일, 반납여부, pk,     // 빌린 사용자? -> OrderSheet을 보고 알 수 있긴 함

		List<RentalRequest> rentalRequests = orderSheetRequest.getRentals();

		List<Order> rentals = new ArrayList<>();
		for (RentalRequest rentalRequest : rentalRequests) {
			Product product = productRepository.findById(rentalRequest.getProductId())
				.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));

			rentProduct(rentals, rentalRequest, product);
		}

		return rentals;
	}

	private void rentProduct(List<Order> rentals, RentalRequest rentalRequest, Product product) {

		LocalDateTime startDateTime = rentalRequest.getStartDateTime();
		LocalDateTime endDateTime = rentalRequest.getEndDateTime();
		DateUtil.validatePeriod(startDateTime, endDateTime);

		Rental rental = Rental.makeRental(product, rentalRequest.getQuantity(),
			startDateTime, endDateTime, rentalRequest.getProductTotalPrice());

		List<ProductRentalUnit> units = productRentalUnitRepository.findAllByProductAndState(
			product, RentalUnitState.RENTAL_AVAILABLE);

		if (units.size() < rentalRequest.getQuantity()) {
			throw new NoStockException(ErrorDetail.NO_STOCK_PRODUCT);
		}

		units = units.subList(0, rentalRequest.getQuantity());

		units.forEach(productUnit -> productUnit.changeState(RentalUnitState.BEING_RENTED));
		rental.assignUnits(units);
		rentals.add(rental);
	}

	@Transactional
	public CUDResponse cancelOrderSheet(Long memberId, Long orderSheetId) {

		Member loginMember = memberRepository.findById(memberId)
			.orElseThrow(() -> new NoSuchMemberException(ErrorDetail.NO_SUCH_MEMBER));

		OrderSheet prevOrderSheet = orderSheetRepository.findById(orderSheetId)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_ORDER_SHEET));

		prevOrderSheet.validateMember(loginMember);

		List<Purchase> purchases = prevOrderSheet.getOrders()
			.stream()
			.filter(Purchase.class::isInstance)
			.map(Purchase.class::cast)
			.collect(Collectors.toList());

		List<Rental> rentals = prevOrderSheet.getOrders()
			.stream()
			.filter(Rental.class::isInstance)
			.map(Rental.class::cast)
			.collect(Collectors.toList());

		purchases.forEach(Purchase::cancel);
		rentals.forEach(Rental::cancel);

		prevOrderSheet.changeState(OrderState.CANCELED);

		return CUDResponse.of(orderSheetId, Message.ORDER_CANCEL_MESSAGE);
	}

	private void validateTotalPrice(Integer orderTotalPrice, Integer calculatedTotalPrice) {
		if (orderTotalPrice.compareTo(calculatedTotalPrice) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_PRICE);
		}
	}
}
