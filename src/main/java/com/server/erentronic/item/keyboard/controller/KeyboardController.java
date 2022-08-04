package com.server.erentronic.item.keyboard.controller;

import com.server.erentronic.item.keyboard.dto.FilterCondition;
import com.server.erentronic.item.keyboard.dto.KeyboardDetailResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardSimpleResponse;
import com.server.erentronic.item.keyboard.service.KeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keyboards")
@RequiredArgsConstructor
public class KeyboardController {

	private final KeyboardService keyboardService;

	@GetMapping
	public Slice<KeyboardSimpleResponse> getKeyboardCards(
		@PageableDefault(size = 9) Pageable pageable, FilterCondition filterCondition) {
		return keyboardService.getKeyboardCards(pageable, filterCondition);
	}

	@GetMapping("/{id}")
	public KeyboardDetailResponse getKeyboardDetail(@PathVariable Long id) {
		return keyboardService.getKeyboardDetail(id);
	}

	@GetMapping("/filters")
	public KeyboardFilterResponse getFilters() {
		return keyboardService.getFilters();
	}
}
