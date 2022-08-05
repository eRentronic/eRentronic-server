package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.common.image.Image;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.type.Vendor;
import com.server.erentronic.item.product.ProductImage;
import com.server.erentronic.item.product.ProductInfoImage;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class KeyboardRequest {

	private String title;

	private String content;

	private Integer price;

	private Integer rentalPrice;

	private Boolean rentable;

	private Integer rentalProductCount;

	private Integer quantity;

	private List<String> productImageUrls;

	private List<String> productInfoImageUrls;

	private Long vendorId;

	private Long connectionId;

	private List<Long> switchIds;

	private Long layoutId;

	public Keyboard toEntity(Vendor vendor, Connection connection, Layout layout,
		List<KeyboardSwitch> keyboardSwitches) {

		Keyboard keyboard = Keyboard.builder()
			.layout(layout)
			.keyboardSwitches(keyboardSwitches)
			.build();

		keyboard.setTitle(title);
		keyboard.setContent(content);
		keyboard.setPrice(price);
		keyboard.setRentalPrice(rentalPrice);
		keyboard.setRentable(rentable);
		keyboard.setRentalProductCount(rentalProductCount);
		keyboard.setQuantity(quantity);
		keyboard.setViewCount(1);
		keyboard.setVendor(vendor);
		keyboard.setConnection(connection);

		productImageUrls.forEach(imageUrl -> keyboard.getProductImages().add(
			ProductImage.of(keyboard, Image.builder().imageUrl(imageUrl).build()))
		);
		productInfoImageUrls.forEach(infoImageUrl -> keyboard.getProductInfoImages().add(
			ProductInfoImage.of(keyboard, Image.builder().imageUrl(infoImageUrl).build()))
		);

		return keyboard;
	}
}
