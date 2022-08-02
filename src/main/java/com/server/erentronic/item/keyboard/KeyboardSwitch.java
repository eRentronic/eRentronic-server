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
}
