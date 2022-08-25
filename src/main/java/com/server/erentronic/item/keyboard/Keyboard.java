package com.server.erentronic.item.keyboard;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductImage;
import com.server.erentronic.item.product.ProductInfoImage;
import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.product.type.Vendor;
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
import org.apache.logging.log4j.util.Strings;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Keyboard extends Product {

	@OneToMany(mappedBy = "keyboard", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE }, orphanRemoval = true)
	private final List<KeyboardSwitch> keyboardSwitches = new ArrayList<>();

	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Layout layout;

	@Builder
	private Keyboard(Layout layout, List<KeyboardSwitch> keyboardSwitches) {
		this.layout = layout;
		if (keyboardSwitches != null) {
			this.keyboardSwitches.addAll(keyboardSwitches);
		}
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
		return super.rentalProductQuantity;
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

	public void modifyTitle(String title) {
		super.title = title;
	}

	public void modifyContent(String content) {
		super.content = content;
	}

	public void updatePrice(Integer price) {
		super.price = price;
	}

	public void updateRentalPrice(Integer rentalPrice) {
		super.rentalPrice = rentalPrice;
	}

	public void changeRentable(Boolean rentable) {
		super.rentable = rentable;
	}

	public void updateRentalProductCount(Integer rentalProductQuantity) {
		super.rentalProductQuantity = rentalProductQuantity;
	}

	public void updateQuantity(Integer quantity) {
		super.quantity = quantity;
	}

	public void updateViewCount(Integer viewCount) {
		super.viewCount = viewCount;
	}

	public void changeKeyboardSwitches(List<KeyboardSwitch> keyboardSwitches) {
		if (keyboardSwitches != null) {
			this.keyboardSwitches.clear();
			this.keyboardSwitches.addAll(keyboardSwitches);
		}
	}

	public void changeProductImages(List<ProductImage> productImages) {
		if (productImages != null) {
			this.productImages.clear();
			this.productImages.addAll(productImages);
		}
	}

	public void changeProductInfoImages(List<ProductInfoImage> productInfoImages) {
		if (productInfoImages != null) {
			this.productInfoImages.clear();
			this.productInfoImages.addAll(productInfoImages);
		}
	}

	public void changeLayout(Layout layout) {
		this.layout = layout;
	}

	public void setVendor(Vendor vendor) {
		super.vendor = vendor;
	}

	public void changeConnection(Connection connection) {
		super.connection = connection;
	}

	public void update(Keyboard updatedKeyboard) {

		if (Strings.isNotBlank(updatedKeyboard.getTitle())) {
			modifyTitle(updatedKeyboard.getTitle());
		}

		if (Strings.isNotBlank(updatedKeyboard.getContent())) {
			modifyContent(updatedKeyboard.getContent());
		}

		if (updatedKeyboard.getPrice() != null) {
			updatePrice(updatedKeyboard.getPrice());
		}

		if (updatedKeyboard.getRentalPrice() != null) {
			updateRentalPrice(updatedKeyboard.getRentalPrice());
		}

		if (updatedKeyboard.getRentable() != null) {
			changeRentable(updatedKeyboard.getRentable());
		}

		if (updatedKeyboard.getQuantity() != null) {
			updateQuantity(updatedKeyboard.getQuantity());
		}

		if (updatedKeyboard.getRentalProductCount() != null) {
			updateRentalProductCount(updatedKeyboard.getRentalProductCount());
		}

		if (updatedKeyboard.getConnection() != null) {
			changeConnection(updatedKeyboard.getConnection());
		}

		if (updatedKeyboard.getLayout() != null) {
			changeLayout(updatedKeyboard.getLayout());
		}

		if (updatedKeyboard.getKeyboardSwitches() != null) {
			changeKeyboardSwitches(updatedKeyboard.getKeyboardSwitches());
		}

		//todo 그냥 다 삭제하고 받은 값으로 이미지를 업데이트 중
		// 값 비교 후 이미 있다면 삭제하지 않고
		if (updatedKeyboard.getProductImages() != null) {
			changeProductImages(getProductImages());
		}

		if (updatedKeyboard.getProductInfoImages() != null) {
			changeProductInfoImages(updatedKeyboard.getProductInfoImages());
		}
	}
}
