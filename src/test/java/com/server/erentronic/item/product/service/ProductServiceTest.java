package com.server.erentronic.item.product.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.repository.ProductRentalUnitRepository;
import com.server.erentronic.item.product.repository.ProductRepository;
import com.server.erentronic.item.product.repository.ProductUnitRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductUnitRepository productUnitRepository;

	@Autowired
	private ProductRentalUnitRepository productRentalUnitRepository;

	@Test
	void matchProductQuantity() {
			List<Product> products = productRepository.findAll();

			for (Product product : products) {
				assertThat(product.getQuantity())
					.isEqualTo(productUnitRepository.countByProduct(product));
				assertThat(product.getRentalProductQuantity())
					.isEqualTo(productRentalUnitRepository.countByProduct(product));
			}
	}
}
