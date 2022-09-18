package com.server.erentronic.item.keyboard.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.erentronic.item.product.type.Vendor;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KeyboardVendorResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -4520628425906000463L;

	private final Long id;

	private final String name;

	public static KeyboardVendorResponse from(Vendor vendor) {
		return new KeyboardVendorResponse(vendor.getId(), vendor.getVendorType().getName());
	}
}
