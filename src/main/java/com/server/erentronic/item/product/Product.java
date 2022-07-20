package com.server.erentronic.item.product;

import com.server.erentronic.common.vendor.Vendor;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Lob
	private String content;

	private Integer price;

	private Integer rentalPrice;

	private Boolean rentable;

	private Integer rentalProductCount;

	private Integer quantity;

	private Integer viewCount;

	@JoinColumn
	@OneToOne
	private Vendor vendor;
}
