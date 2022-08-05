package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.keyboard.type.Connection;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.keyboard.type.Vendor;
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

	public Keyboard toEntity(Vendor vendor, Connection connection, Layout layout, List<KeyboardSwitch> keyboardSwitches) {

		Keyboard keyboard = Keyboard.builder()
			.vendor(vendor)
			.connection(connection)
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
		// todo String 이미지 url -> Image, ProductImage 생성 후 addAll 해야 함
//		keyboard.getProductImages().addAll();
//		keyboard.getProductInfoImages().addAll();
		return keyboard;
	}
}
