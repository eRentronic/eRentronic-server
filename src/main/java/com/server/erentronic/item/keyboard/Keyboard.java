package com.server.erentronic.item.keyboard;

import com.server.erentronic.item.keyboard.type.Connection;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.keyboard.type.Vendor;
import com.server.erentronic.item.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Keyboard extends Product {

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Vendor vendor;

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Connection connection;

	@OneToMany(mappedBy = "keyboard", fetch = FetchType.LAZY)
	private final List<KeyboardSwitch> keyboardSwitches = new ArrayList<>();

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Layout layout;

	@OneToMany(mappedBy = "keyboard", fetch = FetchType.LAZY)
	private final List<KeyboardImage> keyboardImages = new ArrayList<>();

	@OneToMany(mappedBy = "keyboard", fetch = FetchType.LAZY)
	private final List<KeyboardDiscountPolicy> discountPolicies = new ArrayList<>();

	@Builder
	private Keyboard(Vendor vendor, Connection connection, Layout layout) {
		this.vendor = vendor;
		this.connection = connection;
		this.layout = layout;
	}

	public Long getId() {
		return super.id;
	}

	public String getTitle() {
		return super.title;
	}

	public String getContent() {
		return super.content;
	}

	public Integer getPrice() {
		return super.price;
	}

	public Integer getRentalPrice() {
		return super.rentalPrice;
	}

	public Boolean getRentable() {
		return super.rentable;
	}

	public Integer getRentalProductCount() {
		return super.rentalProductCount;
	}

	public Integer getQuantity() {
		return super.quantity;
	}

	public Integer getViewCount() {
		return super.viewCount;
	}
}
