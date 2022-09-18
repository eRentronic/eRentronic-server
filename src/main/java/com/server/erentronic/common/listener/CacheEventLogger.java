package com.server.erentronic.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {

	@Override
	public void onEvent(CacheEvent<?, ?> event) {
		log.info("----------------------------->> Cashed {} List {} {}", event.getType(), event.getNewValue(), event.getOldValue());
	}
}
