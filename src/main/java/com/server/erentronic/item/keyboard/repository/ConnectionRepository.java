package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.product.type.ProductType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

	List<Connection> findAllByProductType(ProductType productType);
}
