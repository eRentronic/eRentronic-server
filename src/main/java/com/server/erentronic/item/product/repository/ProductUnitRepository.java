package com.server.erentronic.item.product.repository;

import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductUnit;
import com.server.erentronic.item.product.UnitState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, Long> {

	int countByProduct(Product product);

	@Query("select pu from ProductUnit pu where pu.product = :product and pu.state = :state")
	List<ProductUnit> findAllByProductAndState(@Param("product") Product product, @Param("state") UnitState state);
}
