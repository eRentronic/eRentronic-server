package com.server.erentronic.item.product.type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "keyboard_connection")
public class Connection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@Enumerated(value = EnumType.STRING)
	private ConnectionType connectionType;

	@Builder
	private Connection(ProductType productType, ConnectionType connectionType) {
		this.productType = productType;
		this.connectionType = connectionType;
	}
}
