package com.server.erentronic.common.order.purchace;

import com.server.erentronic.common.discountpolicy.DiscountPolicy;
import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.common.utils.SalePriceCalculator;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductDiscountPolicy;
import com.server.erentronic.item.product.ProductUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Purchase extends Order {

	@OneToMany
	private List<ProductUnit> units = new ArrayList<>();

	public static Purchase makePurchase(Product product, Integer orderQuantity, Integer orderPrice) {

		product.decreaseQuantity(orderQuantity);

		Purchase purchase = getPurchase(product, orderQuantity,	orderPrice);

		validatePrice(orderPrice, purchase);

		return purchase;
	}

	private static Purchase getPurchase(Product product, Integer orderQuantity, Integer orderPrice) {
		Purchase purchase = new Purchase();

		iterateDiscountPolicies(product, purchase);
		purchase.salePrice = SalePriceCalculator.calculate(product.getPrice() * orderQuantity,
			purchase.totalDiscountRate);
		purchase.product = product;
		purchase.quantity = orderQuantity;
		purchase.price = orderPrice;

		return purchase;
	}

	private static void iterateDiscountPolicies(Product product, Purchase purchase) {
		StringBuilder discountDetailBuilder = new StringBuilder();

		purchase.totalDiscountRate = 0.0;

		for (ProductDiscountPolicy productDiscountPolicy : product.getDiscountPolicies()) {
			DiscountPolicy discountPolicy = productDiscountPolicy.getDiscountPolicy();
			purchase.totalDiscountRate += discountPolicy.getRate();
			discountDetailBuilder.append(discountPolicy).append(System.lineSeparator());
		}
		purchase.discountDetail = discountDetailBuilder.toString();
	}

	private static void validatePrice(Integer orderPrice, Purchase purchase) {
		if (orderPrice.compareTo(purchase.salePrice) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_PRICE);
		}
	}

	public void assignUnits(List<ProductUnit> units) {
		this.units.addAll(units);
	}
}
