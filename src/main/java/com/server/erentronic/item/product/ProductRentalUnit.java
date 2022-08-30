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
public class ProductRentalUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serialNumber;

	@Enumerated(EnumType.STRING)
	private RentalUnitState state;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@Builder
	public ProductRentalUnit(Long id, String serialNumber, RentalUnitState state, Product product) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.state = state;
		this.product = product;
	}

	public static ProductRentalUnit of(String serialNumber, RentalUnitState state, Product product) {

		return ProductRentalUnit.builder()
			.serialNumber(serialNumber)
			.state(state)
			.product(product)
			.build();
	}

	public void changeState(RentalUnitState state) {
		this.state = state;
	}
}
