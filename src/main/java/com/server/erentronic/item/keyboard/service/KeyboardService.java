package com.server.erentronic.item.keyboard.service;

import com.server.erentronic.item.keyboard.dto.KeyboardConnectionResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardSwitchResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardVendorResponse;
import com.server.erentronic.item.keyboard.dto.keyboardLayoutResponse;
import com.server.erentronic.item.keyboard.repository.ConnectionRepository;
import com.server.erentronic.item.keyboard.repository.LayoutRepository;
import com.server.erentronic.item.keyboard.repository.SwitchRepository;
import com.server.erentronic.item.keyboard.repository.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeyboardService {

	private final VendorRepository vendorRepository;
	private final ConnectionRepository connectionRepository;
	private final SwitchRepository switchRepository;
	private final LayoutRepository layoutRepository;

	public KeyboardFilterResponse getFilters() {

		List<KeyboardVendorResponse> vendors = vendorRepository.findAll().stream()
			.map(KeyboardVendorResponse::from)
			.collect(Collectors.toList());
		List<KeyboardConnectionResponse> connections = connectionRepository.findAll().stream()
			.map(KeyboardConnectionResponse::from).collect(Collectors.toList());
		List<KeyboardSwitchResponse> switches = switchRepository.findAll().stream()
			.map(KeyboardSwitchResponse::from)
			.collect(Collectors.toList());
		List<keyboardLayoutResponse> layouts = layoutRepository.findAll().stream()
			.map(keyboardLayoutResponse::from)
			.collect(Collectors.toList());

		return new KeyboardFilterResponse(vendors, connections, switches, layouts);
	}
}
