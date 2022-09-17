package com.server.erentronic.item.product.repository;

import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductRentalUnit;
import com.server.erentronic.item.product.RentalUnitState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRentalUnitRepository extends JpaRepository<ProductRentalUnit, Long> {

	int countByProduct(Product product);

	@Query("select pru from ProductRentalUnit pru where pru.product = :product and pru.state = :state")
	List<ProductRentalUnit> findAllByProductAndState(@Param("product") Product product,
		@Param("state") RentalUnitState state);
}
