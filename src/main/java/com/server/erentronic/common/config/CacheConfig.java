package com.server.erentronic.common.config;

import com.server.erentronic.common.listener.CacheEventLogger;
import com.server.erentronic.item.keyboard.dto.response.KeyboardFilterResponse;
import java.time.Duration;
import javax.cache.CacheManager;
import javax.cache.Caching;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager ehCacheManager() {
		CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

		//로깅을 위한 캐시 리스너 등록
		//https://www.ehcache.org/documentation/3.8/cache-event-listeners.html
		CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
			.newEventListenerConfiguration(new CacheEventLogger(), EventType.CREATED, EventType.EXPIRED)
			.unordered()
			.asynchronous();

		//캐시 설정
		CacheConfigurationBuilder<Object, KeyboardFilterResponse> keyboardFilterConfiguration =
			CacheConfigurationBuilder.newCacheConfigurationBuilder(
					Object.class,
					KeyboardFilterResponse.class,
					ResourcePoolsBuilder
						.newResourcePoolsBuilder()
						.heap(100L, EntryUnit.ENTRIES)
						.offheap(10L, MemoryUnit.MB))
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)))
				.withService(cacheEventListenerConfiguration);

		javax.cache.configuration.Configuration<Object, KeyboardFilterResponse> keyboardFilterCacheConfiguration =
			Eh107Configuration.fromEhcacheCacheConfiguration(keyboardFilterConfiguration);


		cacheManager.createCache("keyboardFilterResponse", keyboardFilterCacheConfiguration);

		return cacheManager;
	}

}
