package com.server.erentronic.item.keyboard.controller;

import com.server.erentronic.item.keyboard.dto.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.service.KeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keyboards")
@RequiredArgsConstructor
public class KeyboardController {

	private final KeyboardService keyboardService;

	@GetMapping("/filter")
	public KeyboardFilterResponse getFilters() {
		 return keyboardService.getFilters();
	}
}
