package com.server.erentronic.item.product.repository;

import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, Long> {

	int countByProduct(Product product);
}
