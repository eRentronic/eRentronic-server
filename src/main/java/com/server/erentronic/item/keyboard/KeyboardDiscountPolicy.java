package com.server.erentronic.item.keyboard;

import com.server.erentronic.common.discountpolicy.DiscountPolicy;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class KeyboardDiscountPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Keyboard keyboard;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private DiscountPolicy discountPolicy;
}
