package com.server.erentronic.common.order;

import com.server.erentronic.item.product.Product;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Table(name = "orders")
public abstract class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	protected OrderSheet orderSheet;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	protected Product product;

	protected Integer quantity;

	protected Integer price;

	public void assignOrderSheet(OrderSheet orderSheet) {
		this.orderSheet = orderSheet;
	}
}
