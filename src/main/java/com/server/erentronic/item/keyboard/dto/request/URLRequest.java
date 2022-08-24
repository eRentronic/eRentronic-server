package com.server.erentronic.item.keyboard.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class URLRequest {

	@URL
	private String url;
}
