package com.server.erentronic.common.order.purchace;

import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.item.product.Product;
import com.server.erentronic.item.product.ProductUnit;
import com.server.erentronic.item.product.UnitState;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Purchase extends Order {

	@OneToMany
	private List<ProductUnit> units = new ArrayList<>();

	public static Purchase makePurchase(Product product, Integer orderQuantity, Integer orderPrice) {

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

	public void cancel() {
		super.product.increaseQuantity(super.quantity);
		units.forEach(unit -> unit.changeState(UnitState.SALE));
	}
}
