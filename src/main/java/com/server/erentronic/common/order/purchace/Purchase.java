package com.server.erentronic.common.order.purchace;

import com.server.erentronic.common.order.Order;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase extends Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
