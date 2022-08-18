package com.server.erentronic.item.keyboard.service;

import static com.server.erentronic.common.message.Message.PRODUCT_CREATED_MESSAGE;
import static com.server.erentronic.item.product.type.ProductType.KEYBOARD;

import com.server.erentronic.common.dto.CreatedResponse;
import com.server.erentronic.common.exception.NoSuchItemException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.keyboard.dto.DeletedResponse;
import com.server.erentronic.item.keyboard.dto.FilterCondition;
import com.server.erentronic.item.keyboard.dto.KeyboardConnectionResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardDetailResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardLayoutResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardRequest;
import com.server.erentronic.item.keyboard.dto.KeyboardSimpleResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardSwitchResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardVendorResponse;
import com.server.erentronic.item.keyboard.repository.ConnectionRepository;
import com.server.erentronic.item.keyboard.repository.KeyboardRepository;
import com.server.erentronic.item.keyboard.repository.LayoutRepository;
import com.server.erentronic.item.keyboard.repository.SwitchRepository;
import com.server.erentronic.item.keyboard.repository.VendorRepository;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.keyboard.type.Switch;
import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.product.type.Vendor;
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

		Slice<Keyboard> slices = keyboardRepository.findByFilterCondition(pageable,
			filterCondition);

		return slices.map(KeyboardSimpleResponse::of);
	}

	@Transactional
	public CreatedResponse postKeyboard(KeyboardRequest keyboardRequest) {
		Long vendorId = keyboardRequest.getVendorId();
		Long connectionId = keyboardRequest.getConnectionId();
		Long layoutId = keyboardRequest.getLayoutId();
		List<Long> switchIds = keyboardRequest.getSwitchIds();

		Vendor vendor = vendorRepository.findById(vendorId)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_VENDOR));
		Connection connection = connectionRepository.findById(connectionId)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_CONNECTION));
		Layout layout = layoutRepository.findById(layoutId)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_LAYOUT));
		List<Switch> switches = switchRepository.findAllById(switchIds);

		Keyboard keyboard = keyboardRequest.toEntity(vendor, connection, layout, switches);

		keyboardRepository.save(keyboard);

		return CreatedResponse.of(keyboard.getId(), PRODUCT_CREATED_MESSAGE.getMessage());
	}

	public KeyboardDetailResponse getKeyboardDetail(Long id) {
		// todo RuntimeException -> CustomException ex) NoSuchElement
		Keyboard keyboard = keyboardRepository.findById(id)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));
		keyboardRepository.findInfoImagesByKeyboardId(id)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));
		keyboardRepository.findSwitchesByKeyboardId(id)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));
		keyboardRepository.findDiscountPoliciesByKeyboardId(id)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));

		return KeyboardDetailResponse.from(keyboard);
	}

	@Transactional
	public DeletedResponse deleteKeyboard(Long id) {
		keyboardRepository.deleteById(id);
		return DeletedResponse.from(id);
	}

	public KeyboardFilterResponse getFilters() {

		List<KeyboardVendorResponse> vendors = vendorRepository.findAllByProductType(KEYBOARD)
			.stream().map(KeyboardVendorResponse::from).collect(Collectors.toList());

		List<KeyboardConnectionResponse> connections = connectionRepository.findAllByProductType(
				KEYBOARD)
			.stream().map(KeyboardConnectionResponse::from).collect(Collectors.toList());

		List<KeyboardSwitchResponse> switches = switchRepository.findAll().stream()
			.map(KeyboardSwitchResponse::from)
			.collect(Collectors.toList());

		List<KeyboardLayoutResponse> layouts = layoutRepository.findAll().stream()
			.map(KeyboardLayoutResponse::from)
			.collect(Collectors.toList());

		return KeyboardFilterResponse.of(vendors, connections, switches, layouts);
	}
}
