package com.server.erentronic.common.order.repository;

import com.server.erentronic.common.order.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {

}
