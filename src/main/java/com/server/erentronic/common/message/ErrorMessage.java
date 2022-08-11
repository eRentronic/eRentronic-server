package com.server.erentronic.common.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {

	INVALID_INPUT_VALUE("입력 값이 올바르지 않습니다.", 400);

	final String message;
	final int status;
}
