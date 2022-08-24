package com.server.erentronic.item.keyboard.controller;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.item.keyboard.dto.request.FilterCondition;
import com.server.erentronic.item.keyboard.dto.response.KeyboardDetailResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.request.KeyboardRequest;
import com.server.erentronic.item.keyboard.dto.response.KeyboardSimpleResponse;
import com.server.erentronic.item.keyboard.dto.request.KeyboardUpdateRequest;
import com.server.erentronic.item.keyboard.service.KeyboardService;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keyboards")
@RequiredArgsConstructor
public class KeyboardController {

	private final KeyboardService keyboardService;

	@GetMapping
	public Slice<KeyboardSimpleResponse> getKeyboardCards(
		@PageableDefault(size = 9) Pageable pageable, @Valid FilterCondition filterCondition) {
		return keyboardService.getKeyboardCards(pageable, filterCondition);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CUDResponse postKeyboard(@RequestBody @Valid KeyboardRequest keyboardRequest) {
		return keyboardService.postKeyboard(keyboardRequest);
	}

	@GetMapping("/{id}")
	public KeyboardDetailResponse getKeyboardDetail(@PathVariable @Positive Long id) {
		return keyboardService.getKeyboardDetail(id);
	}

	@DeleteMapping("/{id}")
	public CUDResponse deleteKeyboard(@PathVariable @Positive Long id) {
		return keyboardService.deleteKeyboard(id);
	}

	@PatchMapping("/{id}")
	public CUDResponse updateKeyboard(
		@PathVariable @Positive Long id,
		@RequestBody @Valid KeyboardUpdateRequest keyboardUpdateRequest
		) {
		return keyboardService.updateKeyboard(id, keyboardUpdateRequest);
	}

	@GetMapping("/filters")
	public KeyboardFilterResponse getFilters() {
		return keyboardService.getFilters();
	}
}
