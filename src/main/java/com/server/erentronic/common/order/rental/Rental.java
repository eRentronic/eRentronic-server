package com.server.erentronic.common.order.rental;

import com.server.erentronic.common.order.Order;
import com.server.erentronic.item.product.ProductRentalUnit;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Rental extends Order {

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;

	@OneToMany
	private List<ProductRentalUnit> units;
}
