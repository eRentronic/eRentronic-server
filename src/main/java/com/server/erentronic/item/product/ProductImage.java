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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	private Image image;
}
