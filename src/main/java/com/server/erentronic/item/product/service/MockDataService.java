package com.server.erentronic.item.product.service;

import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.RentalUnitState;
import com.server.erentronic.item.product.UnitState;
import com.server.erentronic.item.product.repository.ProductRentalUnitRepository;
import com.server.erentronic.item.product.repository.ProductRepository;
import com.server.erentronic.item.product.repository.ProductUnitRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MockDataService {

	private final ProductRepository productRepository;
	private final ProductUnitRepository productUnitRepository;
	private final ProductRentalUnitRepository productRentalUnitRepository;

	@Transactional
	public void initProductStock() {

		List<Product> products = productRepository.findAll();

		for (int i = 0; i < 300; i++) {
			printProductUnit(generateSerialNumber(), UnitState.SALE, generateRandomProductId(products));
		}

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		for (int i = 0; i < 150; i++) {
			printProductRentalUnit(generateSerialNumber(), RentalUnitState.RENTAL_AVAILABLE, generateRandomProductId(products));
		}

		for (Product product : products) {
			product.getId();
		}
		

		for (Product product : products) {
			System.out.println("product" + product.getId() + " count: " + productUnitRepository.countByProduct(product));
			System.out.println("rental product" + product.getId() + " count: " + productRentalUnitRepository.countByProduct(product));
		}

		for (long i = 1; i <= 30; i++) {


		}


	}

	private void printProductUnit(String serialNumber, UnitState state, Product product) {
		System.out.println("(\'" + serialNumber + "\', \'" + state.name() + "\', " + product.getId() + "), ");
	}

	private void printProductRentalUnit(String serialNumber, RentalUnitState state, Product product) {
		System.out.println("(\'" + serialNumber + "\', \'" + state.name() + "\', " + product.getId() + "), ");
	}

	private String generateSerialNumber() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.lastIndexOf('-') + 1);
		return uuid;
	}

	private Product generateRandomProductId(List<Product> products) {
		return products.get((int) ((Math.random() * 1000000) % products.size()));
	}
}
