package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
