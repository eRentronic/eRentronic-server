package com.server.erentronic.item.product.type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keyboard_vendor")
@Getter
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@Enumerated(EnumType.STRING)
	private VendorType vendorType;

	@Builder
	private Vendor(ProductType productType ,VendorType vendorType) {
		this.productType = productType;
		this.vendorType = vendorType;
	}
}
