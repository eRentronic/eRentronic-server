package com.server.erentronic.item.product;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import com.server.erentronic.item.keyboard.ProductDiscountPolicy;
import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.product.type.Vendor;
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	protected Integer rentalProductQuantity;

	protected Integer quantity;

	protected Integer viewCount;

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	protected Vendor vendor;

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	protected Connection connection;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	protected final List<ProductImage> productImages = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	protected final List<ProductInfoImage> productInfoImages = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	protected final List<ProductDiscountPolicy> discountPolicies = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	protected final List<ProductLike> productLikes = new ArrayList<>();

	protected Product(String title, String content, Integer price, Integer rentalPrice,
		Boolean rentable, Integer rentalProductQuantity, Integer quantity, Integer viewCount, Vendor vendor) {

		this.title = title;
		this.content = content;
		this.price = price;
		this.rentalPrice = rentalPrice;
		this.rentable = rentable;
		this.rentalProductQuantity = rentalProductQuantity;
		this.quantity = quantity;
		this.viewCount = viewCount;
		this.vendor = vendor;
	}
}
