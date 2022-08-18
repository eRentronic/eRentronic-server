package com.server.erentronic.item.product.dto;

import com.server.erentronic.item.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductResponse {

	private final Long id;

	private final String title;

	private final String content;

	private final String imageUrl;

	private final Integer price;

	private final Integer quantity;

	private final Integer rentalPrice;

	private final Integer rentalProductQuantity;

	private final Boolean rentable;

	private final Boolean like;

	private final Boolean inBucket;

	public static ProductResponse from(Product product) {
		return new ProductResponse(
			product.getId(),
			product.getTitle(),
			product.getContent(),
			product.getProductImages().get(0).getImage().getImageUrl(),
			product.getPrice(),
			product.getQuantity(),
			product.getRentalPrice(),
			product.getRentalProductQuantity(),
			product.getRentable(),
			false,
			false
		);
	}
}
