package com.server.erentronic.common.order;

import com.server.erentronic.common.address.Address;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.message.ErrorDetail;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "orderSheet", cascade = CascadeType.PERSIST)
	private final List<Order> orders = new ArrayList<>();

	@JoinColumn
	@OneToOne
	private Member member;

	@Embedded
	private Address address;

	private Integer totalPrice;

	@Enumerated(EnumType.STRING)
	private OrderState state;

	@CreatedDate
	private LocalDateTime createdAt;

	public void addOrders(List<Order> orders) {
		if (orders != null) {
			this.orders.addAll(orders);
		}
	}

	@Builder
	public OrderSheet(List<Order> orders, Member member, Address address, Integer totalPrice) {
		this.orders.addAll(orders);
		this.member = member;
		this.address = address;
		this.totalPrice = totalPrice;
		this.state = OrderState.PREPARING_SHIPPING;
	}

	public void changeState(OrderState state) {
		this.state = state;
	}

	public static OrderSheet makeOrderSheet(List<Order> orders, Member member, Address address,
		Integer totalPrice) {

		OrderSheet orderSheet = OrderSheet.builder()
			.orders(orders)
			.member(member)
			.address(address)
			.totalPrice(totalPrice)
			.build();

		for (Order order : orders) {
			order.assignOrderSheet(orderSheet);
		}

		return orderSheet;
	}

	public void validateMember(Member member) {
		if (this.member != member) {
			throw new NotMatchException(ErrorDetail.NOT_MATCH_ORDER_SHEET_MEMBER);
		}
	}
}
