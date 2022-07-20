package com.server.erentronic.common.order.rental;

import com.server.erentronic.common.order.Order;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rental extends Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;
}
