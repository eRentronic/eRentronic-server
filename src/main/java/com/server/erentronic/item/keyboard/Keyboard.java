package com.server.erentronic.item.keyboard;

import com.server.erentronic.item.keyboard.type.Connection;
import com.server.erentronic.item.keyboard.type.Layout;
import com.server.erentronic.item.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Keyboard extends Product {

	@JoinColumn
	@OneToOne
	private Connection connection;

	@OneToMany(mappedBy = "keyboard")
	private final List<KeyboardSwitch> keyboardSwitches = new ArrayList<>();

	@JoinColumn
	@OneToOne
	private Layout layout;
}
