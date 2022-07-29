package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.keyboard.Keyboard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyboardRepository extends JpaRepository<Keyboard, Long>, KeyboardRepositoryCustom {

	Slice<Keyboard> findBy(Pageable page);
}
