package com.server.erentronic.item.product;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import com.server.erentronic.common.image.Image;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductInfoImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	private Image image;

	@Builder
	private ProductInfoImage(Long id, Product product, Image image) {
		this.id = id;
		this.product = product;
		this.image = image;
	}

	public static ProductInfoImage of(Product product, Image image) {

		return ProductInfoImage.builder()
			.id(null)
			.product(product)
			.image(image)
			.build();
	}
}
