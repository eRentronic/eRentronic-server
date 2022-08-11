package com.server.erentronic.item.keyboard;

import com.server.erentronic.item.keyboard.type.Switch;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyboardSwitch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Keyboard keyboard;

	@JoinColumn(name = "switch_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Switch aSwitch;

	@Builder
	private KeyboardSwitch (Long id, Keyboard keyboard, Switch aSwitch) {
		this.id = id;
		this.keyboard = keyboard;
		this.aSwitch = aSwitch;
	}

	public static KeyboardSwitch of(Keyboard keyboard, Switch aSwitch) {

		return KeyboardSwitch.builder()
			.id(null)
			.keyboard(keyboard)
			.aSwitch(aSwitch)
			.build();
	}

	public Switch getSwitch() {
		return aSwitch;
	}
}
