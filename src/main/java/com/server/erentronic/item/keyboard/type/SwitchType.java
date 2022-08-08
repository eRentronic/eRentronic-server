package com.server.erentronic.item.keyboard.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SwitchType {

	BLUE("청축"), 
	BROWN("갈축"), 
	RED("적축"), 
	BLACK("흑축"), 
	SILENCE_RED("무소음 적축");

	private final String name;
}
