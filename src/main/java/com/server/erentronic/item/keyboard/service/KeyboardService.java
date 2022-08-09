package com.server.erentronic.item.keyboard.service;

import static com.server.erentronic.common.message.Message.PRODUCT_CREATED_MESSAGE;
import static com.server.erentronic.common.message.Message.PRODUCT_DELETED_MESSAGE;
import static com.server.erentronic.common.message.Message.PRODUCT_PATCHED_MESSAGE;
import static com.server.erentronic.item.product.type.ProductType.KEYBOARD;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.exception.NoSuchItemException;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.keyboard.dto.FilterCondition;
import com.server.erentronic.item.keyboard.dto.KeyboardConnectionResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardDetailResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardLayoutResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardRequest;
import com.server.erentronic.item.keyboard.dto.KeyboardSimpleResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardSwitchResponse;
import com.server.erentronic.item.keyboard.dto.KeyboardUpdateRequest;
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
	public CUDResponse postKeyboard(KeyboardRequest keyboardRequest) {
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

		return CUDResponse.of(keyboard.getId(), PRODUCT_CREATED_MESSAGE);
	}

	@Transactional
	public CUDResponse updateKeyboard(Long id, KeyboardUpdateRequest keyboardUpdateRequest) {
		Long connectionId = keyboardUpdateRequest.getConnectionId();
		Long layoutId = keyboardUpdateRequest.getLayoutId();
		List<Long> switchIds = keyboardUpdateRequest.getSwitchIds();

		Keyboard keyboard = keyboardRepository.findById(id)
			.orElseThrow(RuntimeException::new);

		Connection connection = null;
		if (connectionId != null) {
			connection = connectionRepository.findById(connectionId)
				.orElseThrow(RuntimeException::new);
		}

		Layout layout = null;
		if (layoutId != null) {
			layout = layoutRepository.findById(layoutId)
				.orElseThrow(RuntimeException::new);
		}

		List<KeyboardSwitch> switches = null;
		if (switchIds != null) {
			switches = switchRepository.findAllById(switchIds).stream()
				.map(aSwitch -> KeyboardSwitch.of(keyboard, aSwitch))
				.collect(Collectors.toList());
		}

		// Keyboard update 메서드에서 TODO 이미지를 변경할 때 기존에 등록되었던 이미지들을 어떻게 처리할지?
		keyboard.update(keyboardUpdateRequest.toEntity(connection, layout, switches));
//		keyboardUpdateRequest.getProductImageUrls();
//		keyboardUpdateRequest.getProductInfoImageUrls();

		return CUDResponse.of(keyboard.getId(), PRODUCT_PATCHED_MESSAGE);
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
	public CUDResponse deleteKeyboard(Long id) {
		keyboardRepository.deleteById(id);
		return CUDResponse.of(id, PRODUCT_DELETED_MESSAGE);
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
