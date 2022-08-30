package com.server.erentronic.item.keyboard.dto.request;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.ProductState;
import com.server.erentronic.item.product.type.Connection;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardUpdateRequest {

	@Nullable
	private String title;

	@Nullable
	private String content;

	@Nullable
	private Integer price;

	@Nullable
	private Integer rentalPrice;

	@Nullable
	private Boolean rentable;

	@Nullable
	private Integer rentalProductCount;

	@Nullable
	@PositiveOrZero
	private Integer quantity;

	@Nullable
	@Valid
	private List<URLRequest> productImageUrls;

	@Nullable
	@Valid
	private List<URLRequest> productInfoImageUrls;

	@Nullable
	private Long connectionId;

	@Nullable
	private List<Long> switchIds;

	@Nullable
	private Long layoutId;

	public Keyboard convertToInstance(Connection connection, Layout layout, List<KeyboardSwitch> keyboardSwitches) {

		Keyboard keyboard = Keyboard.builder().build();
		keyboard.changeConnection(connection);
		keyboard.changeLayout(layout);
		keyboard.changeKeyboardSwitches(keyboardSwitches);
		keyboard.modifyTitle(title);
		keyboard.modifyContent(content);
		keyboard.updatePrice(price);
		keyboard.updateRentalPrice(rentalPrice);
		keyboard.changeRentable(rentable);
		keyboard.updateRentalProductCount(rentalProductCount);
		keyboard.updateQuantity(quantity);
		keyboard.changeProductState(ProductState.COMMERCIAL);

		return keyboard;
	}
}
