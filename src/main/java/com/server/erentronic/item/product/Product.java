package com.server.erentronic.item.product;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String title;

	@Lob
	protected String content;

	protected Integer price;

	protected Integer rentalPrice;

	protected Boolean rentable;

	protected Integer rentalProductCount;

	protected Integer quantity;

	protected Integer viewCount;

	protected Product(String title, String content, Integer price, Integer rentalPrice,
		Boolean rentable, Integer rentalProductCount, Integer quantity, Integer viewCount) {

		this.title = title;
		this.content = content;
		this.price = price;
		this.rentalPrice = rentalPrice;
		this.rentable = rentable;
		this.rentalProductCount = rentalProductCount;
		this.quantity = quantity;
		this.viewCount = viewCount;
	}
}
