package com.server.erentronic.item.keyboard.type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SwitchType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
