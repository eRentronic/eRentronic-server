package com.server.erentronic.item.keyboard.service;

import static com.server.erentronic.common.message.Message.PRODUCT_CREATED_MESSAGE;
import static com.server.erentronic.common.message.Message.PRODUCT_DELETED_MESSAGE;
import static com.server.erentronic.common.message.Message.PRODUCT_PATCHED_MESSAGE;
import static com.server.erentronic.item.product.type.ProductType.KEYBOARD;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.exception.NoSuchItemException;
import com.server.erentronic.common.image.Image;
import com.server.erentronic.common.message.ErrorDetail;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.KeyboardSwitch;
import com.server.erentronic.item.keyboard.dto.request.FilterCondition;
import com.server.erentronic.item.keyboard.dto.request.KeyboardRequest;
import com.server.erentronic.item.keyboard.dto.request.KeyboardUpdateRequest;
import com.server.erentronic.item.keyboard.dto.request.URLRequest;
import com.server.erentronic.item.keyboard.dto.response.KeyboardConnectionResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardDetailResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardFilterResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardLayoutResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardSimpleResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardSwitchResponse;
import com.server.erentronic.item.keyboard.dto.response.KeyboardVendorResponse;
import com.server.erentronic.item.keyboard.repository.ConnectionRepository;
import com.server.erentronic.item.keyboard.repository.KeyboardRepository;
import com.server.erentronic.item.keyboard.repository.LayoutRepository;
import com.server.erentronic.item.keyboard.repository.SwitchRepository;
import com.server.erentronic.item.keyboard.repository.VendorRepository;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.keyboard.type.Switch;
import com.server.erentronic.item.product.ProductImage;
import com.server.erentronic.item.product.ProductInfoImage;
import com.server.erentronic.item.product.ProductState;
import com.server.erentronic.item.product.type.Connection;
import com.server.erentronic.item.product.type.Vendor;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));

		Connection connection = null;
		if (connectionId != null) {
			connection = connectionRepository.findById(connectionId)
				.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_CONNECTION));
		}

		Layout layout = null;
		if (layoutId != null) {
			layout = layoutRepository.findById(layoutId)
				.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_LAYOUT));
		}

		List<KeyboardSwitch> switches = null;
		if (switchIds != null) {
			switches = switchRepository.findAllById(switchIds).stream()
				.map(aSwitch -> KeyboardSwitch.of(keyboard, aSwitch))
				.collect(Collectors.toList());
		}

		keyboard.update(keyboardUpdateRequest.convertToInstance(connection, layout, switches));

		//todo Keyboard update 메서드에서 이미지를 변경할 때 기존에 등록되었던 이미지들을 어떻게 처리할지?
		// 현재는 기존 이미지에 이미지들을 추가하고 있음
		List<URLRequest> productImageUrls = keyboardUpdateRequest.getProductImageUrls();
		if (productImageUrls != null) {
			productImageUrls.forEach(imageUrl -> keyboard.getProductImages().add(
				ProductImage.of(keyboard,
					Image.builder().imageUrl(imageUrl.getUrl()).build()))
			);
		}

		List<URLRequest> productInfoImageUrls = keyboardUpdateRequest.getProductInfoImageUrls();
		if (productInfoImageUrls != null) {
			productInfoImageUrls.forEach(infoImageUrl -> keyboard.getProductInfoImages().add(
				ProductInfoImage.of(keyboard,
					Image.builder().imageUrl(infoImageUrl.getUrl()).build()))
			);
		}

		return CUDResponse.of(keyboard.getId(), PRODUCT_PATCHED_MESSAGE);
	}

	public KeyboardDetailResponse getKeyboardDetail(Long id) {
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

	public List<KeyboardSimpleResponse> recommendKeyboards(Long id) {
		//todo 나중에 바꿔야 함
		// 로직을 어떻게 할지는 머리아픔
		List<Keyboard> keyboards = keyboardRepository.findAll();
		Collections.shuffle(keyboards);
		return keyboards.subList(0, 5).stream()
			.map(KeyboardSimpleResponse::of)
			.collect(Collectors.toList());
	}

	@Transactional
	public CUDResponse deleteKeyboard(Long id) {
		Keyboard keyboard = keyboardRepository.findById(id)
			.orElseThrow(() -> new NoSuchItemException(ErrorDetail.NO_SUCH_PRODUCT));
		keyboard.changeProductState(ProductState.EXTINCTION);
		return CUDResponse.of(id, PRODUCT_DELETED_MESSAGE);
	}

	@Cacheable(cacheNames = "keyboardFilterResponse")
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
