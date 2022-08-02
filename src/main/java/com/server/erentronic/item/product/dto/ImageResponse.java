package com.server.erentronic.item.product.dto;

import com.server.erentronic.common.image.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ImageResponse {

	private final Long id;

	private final String imageUrl;

	public static ImageResponse from(Image image) {
		return new ImageResponse(image.getId(), image.getImageUrl());
	}
}
