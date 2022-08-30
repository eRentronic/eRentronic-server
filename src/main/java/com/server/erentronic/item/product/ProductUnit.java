package com.server.erentronic.item.product;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serialNumber;

	@Enumerated(EnumType.STRING)
	private UnitState state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Product product;

	@Builder
	public ProductUnit(Long id, String serialNumber, UnitState state, Product product) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.state = state;
		this.product = product;
	}

	public static ProductUnit of(String serialNumber, UnitState state, Product product) {

		return ProductUnit.builder()
			.serialNumber(serialNumber)
			.state(state)
			.product(product)
			.build();
	}

	public void changeState(UnitState state) {
		this.state = state;
	}
}
