package com.server.erentronic.common.vendor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VendorType {

	LOGITECH("로지텍"),
	ABKO("앱코"),
	COX("콕스"),
	LEOPLOD("레오폴드"),
	HAPPYHACKING("해피해킹"),
	REALFORCE("리얼포스");
	
	private final String name;
}
