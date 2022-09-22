package com.server.erentronic.common.order.service;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.exception.InvalidInputException;
import com.server.erentronic.common.exception.NoStockException;
import com.server.erentronic.common.exception.NoSuchItemException;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.repository.MemberRepository;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.message.Message;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.order.OrderSheet;
import com.server.erentronic.common.order.OrderState;
import com.server.erentronic.common.order.dto.OrderSheetRequest;
import com.server.erentronic.common.order.dto.PurchaseRequest;
import com.server.erentronic.common.order.dto.RentalRequest;
import com.server.erentronic.common.order.purchace.Purchase;
import com.server.erentronic.common.order.rental.Rental;
import com.server.erentronic.common.order.repository.OrderSheetRepository;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductRentalUnit;
import com.server.erentronic.item.product.ProductUnit;
import com.server.erentronic.item.product.RentalUnitState;
import com.server.erentronic.item.product.UnitState;
import com.server.erentronic.item.product.repository.ProductRentalUnitRepository;
import com.server.erentronic.item.product.repository.ProductRepository;
import com.server.erentronic.item.product.repository.ProductUnitRepository;
import java.time.LocalDateTime;
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
	private final ProductRentalUnitRepository productRentalUnitRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public CUDResponse order(Member loginMember, OrderSheetRequest orderSheetRequest) {
		//todo 로그인 기능 구현 후 제거해야 함
		loginMember = memberRepository.findById(1L).get();

		List<Order> purchases = purchase(loginMember, orderSheetRequest);
		List<Order> rentals = rent(loginMember, orderSheetRequest);

		List<Order> orders = new ArrayList<>(purchases);
		orders.addAll(rentals);

		Integer totalPrice = orders.stream().mapToInt(Order::getSalePrice).sum();
		validateTotalPrice(orderSheetRequest.getTotalPrice(), totalPrice);

		OrderSheet orderSheet = OrderSheet.makeOrderSheet(orders, loginMember,
			orderSheetRequest.getAddress(), orderSheetRequest.getTotalPrice());

		orderSheetRepository.save(orderSheet);

		return CUDResponse.of(orderSheet.getId(), Message.ORDER_SUCCESS_MESSAGE);
	}

	private List<Order> purchase(Member loginMember, OrderSheetRequest orderSheetRequest) {

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

		List<ProductUnit> units = productUnitRepository.findAllByProductAndState(product, UnitState.SALE);

		if (units.size() < purchaseRequest.getQuantity()) {
			throw new NoStockException(ErrorDetail.NO_STOCK_PRODUCT);
		}

		units = units.subList(0, purchaseRequest.getQuantity());

		units.forEach(productUnit -> productUnit.changeState(UnitState.SOLD_OUT));
		purchase.assignUnits(units);

		purchases.add(purchase);
	}

	private List<Order> rent(Member loginMember, OrderSheetRequest orderSheetRequest) {

		//todo
		// 렌탈도 동시성 나중에 생각
		// 렌탈은 반납 일정 관리를 해야하기 때문에 새로운 테이블에 저장해야 함
		// 새로운 테이블은 OrderSheet fk, 나간 상품 Unit fk, 대여일, 반납일, 반납여부, pk

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
		validateRentalPeriod(startDateTime, endDateTime);

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
	public CUDResponse cancelOrderSheet(Member loginMember, Long orderSheetId) {
		//todo loginMember와 orderSheet의 멤버가 동일한 사람인지 판별해야 함

		OrderSheet prevOrderSheet = orderSheetRepository.findById(orderSheetId)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_ORDER_SHEET));

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
  
  private void validateRentalPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())
			|| startDateTime.toLocalDate().isAfter(endDateTime.toLocalDate())) {
			throw new InvalidInputException(ErrorDetail.INVALID_RENTAL_PERIOD);
		}
	}

	private void validateTotalPrice(Integer orderTotalPrice, Integer calculatedTotalPrice) {
		if (orderTotalPrice.compareTo(calculatedTotalPrice) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_PRICE);
		}
	}
}
