package com.server.erentronic.common.address;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Address {

	private String fullAddress;

	private String address1;

	private String address2;

	private String zipCode;
}
