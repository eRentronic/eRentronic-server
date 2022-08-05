package com.server.erentronic.item.keyboard;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.type.Vendor;
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

	@OneToMany(mappedBy = "keyboard", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	private final List<KeyboardSwitch> keyboardSwitches = new ArrayList<>();

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Layout layout;

	@Builder
	private Keyboard(Layout layout, List<KeyboardSwitch> keyboardSwitches) {
		this.layout = layout;
		this.keyboardSwitches.addAll(keyboardSwitches);
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

	public Vendor getVendor() {
		return super.vendor;
	}

	public Connection getConnection() {
		return super.connection;
	}

	public void setId(Long id) {
		super.id = id;
	}

	public void setTitle(String title) {
		super.title = title;
	}

	public void setContent(String content) {
		super.content = content;
	}

	public void setPrice(Integer price) {
		super.price = price;
	}

	public void setRentalPrice(Integer rentalPrice) {
		super.rentalPrice = rentalPrice;
	}

	public void setRentable(Boolean rentable) {
		super.rentable = rentable;
	}

	public void setRentalProductCount(Integer rentalProductCount) {
		super.rentalProductCount = rentalProductCount;
	}

	public void setQuantity(Integer quantity) {
		super.quantity = quantity;
	}

	public void setViewCount(Integer viewCount) {
		super.viewCount = viewCount;
	}

	public void setKeyboardSwitches(List<KeyboardSwitch> keyboardSwitches) {
		this.keyboardSwitches.addAll(keyboardSwitches);
	}

	public void setVendor(Vendor vendor) {
		super.vendor = vendor;
	}

	public void setConnection(Connection connection) {
		super.connection = connection;
	}
}
