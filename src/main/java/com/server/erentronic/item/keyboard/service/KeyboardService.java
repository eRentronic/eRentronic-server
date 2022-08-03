package com.server.erentronic.item.keyboard.service;

import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.dto.FilterCondition;
import com.server.erentronic.item.keyboard.dto.KeyboardConnectionResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardDetailResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardSimpleResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardSwitchResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardVendorResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardLayoutResponse;
import com.server.erentronic.item.keyboard.repository.ConnectionRepository;
import com.server.erentronic.item.keyboard.repository.KeyboardRepository;
import com.server.erentronic.item.keyboard.repository.LayoutRepository;
import com.server.erentronic.item.keyboard.repository.SwitchRepository;
import com.server.erentronic.item.keyboard.repository.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeyboardService {

	private final KeyboardRepository keyboardRepository;

	private final VendorRepository vendorRepository;
	private final ConnectionRepository connectionRepository;
	private final SwitchRepository switchRepository;
	private final LayoutRepository layoutRepository;

	public Slice<KeyboardSimpleResponse> getKeyboardCards(Pageable pageable,
		FilterCondition filterCondition) {

		Slice<Keyboard> slices = keyboardRepository.findByFilterCondition(pageable, filterCondition);
		return slices.map(KeyboardSimpleResponse::of);
	}

	public KeyboardDetailResponse getKeyboardDetail(Long id) {
		Keyboard keyboard = keyboardRepository.findById(id).orElseThrow(RuntimeException::new);
		keyboardRepository.findInfoImagesByKeyboardId(id);
		keyboardRepository.findSwitchesByKeyboardId(id);
		keyboardRepository.findDiscountPoliciesByKeyboardId(id);

		return KeyboardDetailResponse.from(keyboard);
	}

	public KeyboardFilterResponse getFilters() {

		List<KeyboardVendorResponse> vendors = vendorRepository.findAll().stream()
			.map(KeyboardVendorResponse::from)
			.collect(Collectors.toList());
		List<KeyboardConnectionResponse> connections = connectionRepository.findAll().stream()
			.map(KeyboardConnectionResponse::from).collect(Collectors.toList());
		List<KeyboardSwitchResponse> switches = switchRepository.findAll().stream()
			.map(KeyboardSwitchResponse::from)
			.collect(Collectors.toList());
		List<KeyboardLayoutResponse> layouts = layoutRepository.findAll().stream()
			.map(KeyboardLayoutResponse::from)
			.collect(Collectors.toList());

		return KeyboardFilterResponse.of(vendors, connections, switches, layouts);
	}
}
