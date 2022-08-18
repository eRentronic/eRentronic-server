package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.keyboard.Keyboard;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeyboardRepository extends JpaRepository<Keyboard, Long>, KeyboardRepositoryCustom {

	Slice<Keyboard> findBy(Pageable page);

	// vendor, connection, layout, keyboardImages
	@Query("select k from Keyboard k "
		+ "join fetch k.vendor "
		+ "join fetch k.connection "
		+ "join fetch k.layout "
		+ "join fetch k.productImages pi "
		+ "join fetch pi.image "
		+ "where k.id = :id")
	Optional<Keyboard> findById(@Param("id") Long id);

	// infoImages
	@Query("select k from Keyboard k "
		+ "join fetch k.productInfoImages pii "
		+ "join fetch pii.image "
		+ "where k.id = :keyboardId")
	Optional<Keyboard> findInfoImagesByKeyboardId(@Param("keyboardId") Long id);

	// switch
	@Query("select k from Keyboard k "
		+ "join fetch k.keyboardSwitches ks "
		+ "join fetch ks.aSwitch "
		+ "where k.id = :keyboardId")
	Optional<Keyboard> findSwitchesByKeyboardId(@Param("keyboardId") Long id);

	// discountPolicy
	@Query("select k from Keyboard k "
		+ "left join fetch k.discountPolicies dp "
		+ "left join fetch dp.discountPolicy "
		+ "where k.id = :keyboardId")
	Optional<Keyboard> findDiscountPoliciesByKeyboardId(@Param("keyboardId") Long id);
}
