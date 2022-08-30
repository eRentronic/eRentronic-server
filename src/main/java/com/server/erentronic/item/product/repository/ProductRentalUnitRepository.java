package com.server.erentronic.item.product.repository;

import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductRentalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRentalUnitRepository extends JpaRepository<ProductRentalUnit, Long> {

	int countByProduct(Product product);
}
