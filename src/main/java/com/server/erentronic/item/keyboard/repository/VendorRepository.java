package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.product.type.ProductType;
import com.server.erentronic.item.product.type.Vendor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

	List<Vendor> findAllByProductType(ProductType productType);
}
