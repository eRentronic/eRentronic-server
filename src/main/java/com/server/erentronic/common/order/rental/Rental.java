package com.server.erentronic.common.order.rental;

import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductRentalUnit;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Rental extends Order {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime startDateTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime endDateTime;

	@OneToMany
	private List<ProductRentalUnit> units = new ArrayList<>();

	public static Rental makeRental(Product product, Integer orderQuantity, LocalDateTime startDateTime,
		LocalDateTime endDateTime, Integer orderPrice) {

		int rentalPeriod = (int) ChronoUnit.DAYS.between(startDateTime, endDateTime);

		if (orderPrice.compareTo(product.getRentalPrice() * rentalPeriod * orderQuantity) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_PRICE);
		}

		product.decreaseRentalQuantity(orderQuantity);

		Rental rental = new Rental();
		rental.product = product;
		rental.quantity = orderQuantity;
		rental.price = orderPrice;
		rental.startDateTime = startDateTime;
		rental.endDateTime = endDateTime;

		return rental;
	}

	public void assignUnits(List<ProductRentalUnit> units) {
		this.units.addAll(units);
	}
}
