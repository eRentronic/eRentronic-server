package com.server.erentronic.item.keyboard;

import com.server.erentronic.common.vendor.Vendor;
import com.server.erentronic.item.keyboard.type.Connection;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyboard extends Product {

	@JoinColumn
	@OneToOne
	private Connection connection;

	@OneToMany(mappedBy = "keyboard")
	private final List<KeyboardSwitch> keyboardSwitches = new ArrayList<>();

	@JoinColumn
	@OneToOne
	private Layout layout;

	@OneToMany(mappedBy = "keyboard")
	private final List<KeyboardDiscountPolicy> discountPolicies = new ArrayList<>();

	@Builder
	private Keyboard(Connection connection, Layout layout, Vendor vendor) {
		// TODO super(vendor);
		super.vendor = vendor;
		this.connection = connection;
		this.layout = layout;
	}
}
