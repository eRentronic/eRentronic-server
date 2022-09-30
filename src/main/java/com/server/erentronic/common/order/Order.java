package com.server.erentronic.common.order;

import com.server.erentronic.item.product.Product;
import javax.persistence.Column;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Table(name = "orders")
@Getter
public abstract class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(insertable = false, updatable = false)
	protected String dtype;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	protected OrderSheet orderSheet;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	protected Product product;

	protected Double totalDiscountRate;

	protected String discountDetail; //이벤트 이름, 적용된 할인율, 할인을 적용한 할인가격 -> 만드는 빌더 있어야 할듯?

	protected Integer quantity;

	protected Integer price;
	
	protected Integer salePrice;

	public void assignOrderSheet(OrderSheet orderSheet) {
		this.orderSheet = orderSheet;
	}

	public boolean isOrderType(String dtype) {
		return dtype == null || this.dtype.equals(dtype);
	}
}
