package com.server.erentronic.item.product.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VendorType {

	LOGITECH("로지텍"),
	ABKO("앱코"),
	COX("콕스"),
	LEOPLOD("레오폴드"),
	HAPPYHACKING("해피해킹"),
	REALFORCE("리얼포스");
	
	private final String name;
}
