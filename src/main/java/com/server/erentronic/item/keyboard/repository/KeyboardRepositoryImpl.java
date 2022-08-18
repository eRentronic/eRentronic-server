package com.server.erentronic.item.keyboard.repository;

import static com.server.erentronic.item.keyboard.QKeyboard.keyboard;
import static com.server.erentronic.item.keyboard.QKeyboardSwitch.keyboardSwitch;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.keyboard.QKeyboardSwitch;
import com.server.erentronic.item.keyboard.dto.FilterCondition;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class KeyboardRepositoryImpl implements KeyboardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<Keyboard> findByFilterCondition(Pageable pageable, FilterCondition condition) {

		QKeyboardSwitch subKeyboardSwitch = new QKeyboardSwitch("subKeyboardSwitch");

		List<Keyboard> contents = queryFactory.selectDistinct(keyboard)
			.from(keyboard)
			.leftJoin(keyboard.layout).fetchJoin()
			.leftJoin(keyboard.connection).fetchJoin()
			.leftJoin(keyboard.vendor).fetchJoin()
			.leftJoin(keyboard.keyboardSwitches, keyboardSwitch)
			.where(
				inConditionsWith(keyboard.connection.id, condition.getConnectionIds()),
				inConditionsWith(keyboard.layout.id, condition.getLayoutIds()),
				inConditionsWith(keyboard.vendor.id, condition.getVendorIds()),
				eqAnySwitchIds(condition.getSwitchIds(), subKeyboardSwitch),
				isRentable(condition.getRentable()),
				isPurchasable(condition.getPurchasable()),
				isLike(condition.getKeyword())
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1L)
			.fetch();

		boolean hasNext = false;
		if (contents.size() > pageable.getPageSize()) {
			contents.remove(pageable.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(contents, pageable, hasNext);
	}

	private BooleanExpression eqAnySwitchIds(List<Long> switchIds, QKeyboardSwitch subKeyboardSwitch) {
		if (switchIds == null) {
			return null;
		}

		return keyboardSwitch.eqAny(
			JPAExpressions.selectFrom(subKeyboardSwitch)
				.where(inConditionsWith(subKeyboardSwitch.aSwitch.id, switchIds)));
	}

	private BooleanExpression inConditionsWith(NumberPath<Long> target, List<Long> filters) {
		if (filters == null) {
			return null;
		}
		return target.in(filters);
	}

	private BooleanExpression isRentable(Boolean rentable) {
		if (rentable == null) {
			return null;
		}

		return keyboard.rentable.eq(rentable);
	}

	private BooleanExpression isPurchasable(Boolean purchasable) {
		if (purchasable == null || !purchasable) {
			return null;
		}

		return keyboard.quantity.goe(1);
	}

	private BooleanExpression isLike(String keyword) {
		if (keyword == null) {
			return null;
		}

		return keyboard.title.contains(keyword).or(keyboard.content.contains(keyword));
	}
}
