package com.server.erentronic.common.order.dto;

import com.server.erentronic.common.address.Address;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderSheetRequest {

	@Valid
	private List<PurchaseRequest> purchases;

	@Valid
	private List<RentalRequest> rentals;

	@NotNull
	@Valid
	private Address address;

	@Positive
	private Integer totalPrice;
}
