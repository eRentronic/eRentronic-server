package com.server.erentronic.item.keyboard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KeyboardLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
