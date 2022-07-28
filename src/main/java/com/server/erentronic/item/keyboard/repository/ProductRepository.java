package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.product.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAll();

	Slice<Product> findBy(Pageable page);
}
