package com.server.erentronic.item.keyboard.dto;

import com.server.erentronic.common.image.Image;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.ProductImage;
import com.server.erentronic.item.product.ProductInfoImage;
import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.product.type.Vendor;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class KeyboardRequest {

	@NotBlank(message = "(키보드 상품 명) 필수 입력란 입니다.")
	private String title;

	@NotBlank(message = "(키보드 상품 스펙) 필수 입력란 입니다.")
	private String content;

	@NotNull(message = "(키보드 상품 가격) 필수 입력란 입니다.")
	private Integer price;

	@NotNull(message = "(키보드 렌탈 가격) 필수 입력란 입니다.")
	private Integer rentalPrice;

	@NotNull(message = "(키보드 렌탈가능 여부) 필수 입력란 입니다.")
	private Boolean rentable;

	@NotNull(message = "(렌탈 가능한 키보드 수) 필수 입력란 입니다.")
	private Integer rentalProductCount;

	@NotNull(message = "(판매할 키보드 수) 필수 입력란 입니다.")
	@Positive
	private Integer quantity;

	@NotEmpty(message = "(키보드 이미지) 필수 입력란 입니다.")
	@Valid
	private List<URLRequest> productImageUrls;

	@NotEmpty(message = "(키보드 상세 이미지) 필수 입력란 입니다.")
	@Valid
	private List<URLRequest> productInfoImageUrls;

	@NotNull(message = "(제조사) 필수 입력란 입니다.")
	private Long vendorId;

	@NotNull(message = "(연결 방식) 필수 입력란 입니다.")
	private Long connectionId;

	@NotNull(message = "(키 축) 필수 입력란 입니다.")
	private List<Long> switchIds;

	@NotNull(message = "(키보드 배열) 필수 입력란 입니다.")
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
			ProductImage.of(keyboard, Image.builder().imageUrl(imageUrl.getUrl()).build()))
		);
		productInfoImageUrls.forEach(infoImageUrl -> keyboard.getProductInfoImages().add(
			ProductInfoImage.of(keyboard, Image.builder().imageUrl(infoImageUrl.getUrl()).build()))
		);

		return keyboard;
	}
}
