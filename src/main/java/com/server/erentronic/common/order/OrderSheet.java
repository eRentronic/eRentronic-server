package com.server.erentronic.common.order;

import com.server.erentronic.common.address.Address;
import com.server.erentronic.common.member.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class OrderSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "orderSheet")
	private final List<Order> orders = new ArrayList<>();

	@JoinColumn
	@OneToOne
	private Member member;

	@JoinColumn
	@OneToOne
	private Address address;

	private Integer totalPrice;

	@Enumerated(EnumType.STRING)
	private OrderState state;

	@CreatedDate
	private LocalDateTime createdAt;
}
