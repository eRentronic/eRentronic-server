package com.server.erentronic.item.product.repository;

import com.server.erentronic.item.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
