package com.server.erentronic.item.keyboard;

import com.server.erentronic.common.image.Image;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class KeyboardInfoImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne
	private Keyboard keyboard;

	@JoinColumn
	@ManyToOne
	private Image image;
}
