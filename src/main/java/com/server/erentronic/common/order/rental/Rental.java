package com.server.erentronic.common.order.rental;

import com.server.erentronic.common.discountpolicy.DiscountPolicy;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.utils.SalePriceCalculator;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductDiscountPolicy;
import com.server.erentronic.item.product.ProductRentalUnit;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rental extends Order {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime startDateTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime endDateTime;

	@OneToMany
	private List<ProductRentalUnit> units = new ArrayList<>();

	public static Rental makeRental(Product product, Integer orderQuantity, LocalDateTime startDateTime,
		LocalDateTime endDateTime, Integer orderPrice) {

		product.decreaseRentalQuantity(orderQuantity);

		Rental rental = getRental(product, orderQuantity, orderPrice,
			(int) ChronoUnit.DAYS.between(startDateTime, endDateTime));

		rental.startDateTime = startDateTime;
		rental.endDateTime = endDateTime;

		validatePrice(orderPrice, rental);

		return rental;
	}

	private static Rental getRental(Product product, Integer orderQuantity, Integer orderPrice, int rentalPeriod) {
		Rental rental = new Rental();

		iterateDiscountPolicies(product, rental);

		rental.product = product;
		rental.quantity = orderQuantity;
		rental.price = orderPrice;
		rental.salePrice = SalePriceCalculator.calculate(product.getRentalPrice() * rentalPeriod * orderQuantity,
			rental.totalDiscountRate);


		return rental;
	}

	private static void iterateDiscountPolicies(Product product, Rental rental) {
		StringBuilder discountDetailBuilder = new StringBuilder();


		rental.totalDiscountRate = 0.0;

		for (ProductDiscountPolicy productDiscountPolicy : product.getDiscountPolicies()) {
			DiscountPolicy discountPolicy = productDiscountPolicy.getDiscountPolicy();
			rental.totalDiscountRate += discountPolicy.getRate();
			discountDetailBuilder.append(discountPolicy).append(System.lineSeparator());
		}
		rental.discountDetail = discountDetailBuilder.toString();

	}

	private static void validatePrice(Integer orderPrice, Rental rental) {
		if (orderPrice.compareTo(rental.salePrice) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_PRICE);
		}
	}

	public void assignUnits(List<ProductRentalUnit> units) {
		this.units.addAll(units);
	}
}
