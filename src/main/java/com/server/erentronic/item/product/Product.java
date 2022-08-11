package com.server.erentronic.item.product;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import com.server.erentronic.item.keyboard.ProductDiscountPolicy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	protected final List<ProductImage> productImages = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	protected final List<ProductInfoImage> productInfoImages = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	protected final List<ProductDiscountPolicy> discountPolicies = new ArrayList<>();

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
