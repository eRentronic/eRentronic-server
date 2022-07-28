package com.server.erentronic.item.keyboard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KeyboardSimpleResponse {

	private ProductSimpleResponse productSimpleResponse;

//	private Long id;
//
//	private String title;
//
//	private String content;
//
//	private String imageUrl;
//
//	private Integer price;
//
//	private Integer rentalPrice;
//
//  private KeyboardDiscountResponse discounts;
//
//	private Integer salePrice;
//
//	private Integer saleRentalPrice;
//
//	private Boolean rentalable;
//
//	private Boolean like;

	private KeyboardVendorResponse vendor;
}
