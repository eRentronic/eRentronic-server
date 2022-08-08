package com.server.erentronic.item.keyboard.repository;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.dto.FilterCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface KeyboardRepositoryCustom {

	Slice<Keyboard> findByFilterCondition(Pageable pageable, FilterCondition condition);
}
