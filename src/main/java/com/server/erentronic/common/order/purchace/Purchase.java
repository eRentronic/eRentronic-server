package com.server.erentronic.common.order.purchace;

import com.server.erentronic.common.exception.NotMatchException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.common.order.Order;
import com.server.erentronic.item.product.Product;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends Order {

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
}
