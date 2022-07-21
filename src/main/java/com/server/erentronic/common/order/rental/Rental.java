package com.server.erentronic.common.order.rental;

import com.server.erentronic.common.order.Order;
import java.time.LocalDateTime;
import javax.persistence.Entity;

@Entity
public class Rental extends Order {

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;
}
