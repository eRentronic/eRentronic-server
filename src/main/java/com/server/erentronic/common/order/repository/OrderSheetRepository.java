package com.server.erentronic.common.order.repository;

import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.order.OrderSheet;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {

	@Query("select o from OrderSheet o "
		+ "join fetch o.orders "
		+ "where (o.createdAt between :start and :end) "
		+ "and (o.member = :member)")
	List<OrderSheet> findWithinSpecificPeriod(@Param("member") Member member,
		@Param("start") LocalDateTime startDateTime, @Param("end") LocalDateTime endDateTime);
}
