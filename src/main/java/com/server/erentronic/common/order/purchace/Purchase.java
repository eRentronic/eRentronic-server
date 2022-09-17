package com.server.erentronic.common.order.purchace;

import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends Order {

	@OneToMany
	private List<ProductUnit> units = new ArrayList<>();

	public static Purchase makePurchase(Product product, Integer orderQuantity, Integer orderPrice) {

		//todo 할인가격 적용해야 함
		if (orderPrice.compareTo(product.getPrice() * orderQuantity) != 0) {
			throw new NotMatchException(ErrorDetail.NOT_EQUALS_REAL_PRICE);
		}
		
		product.decreaseQuantity(orderQuantity);

		Purchase purchase = new Purchase();
		purchase.product = product;
		purchase.quantity = orderQuantity;
		purchase.price = orderPrice;

		return purchase;
	}

	public void assignUnits(List<ProductUnit> units) {
		this.units.addAll(units);
	}
}
