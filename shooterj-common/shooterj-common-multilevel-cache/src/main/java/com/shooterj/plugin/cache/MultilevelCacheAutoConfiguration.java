package com.shooterj.plugin.cache;

import com.shooterj.plugin.cache.properties.CacheConfigProperties;
import com.shooterj.plugin.cache.support.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

@AutoConfiguration(after = RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheConfigProperties.class)
public class MultilevelCacheAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(RedisTemplate.class)
	public RedisCaffeineCacheManager cacheManager(CacheConfigProperties cacheConfigProperties,
												  @Qualifier("stringKeyRedisTemplate") RedisTemplate<Object, Object> stringKeyRedisTemplate,
												  ObjectProvider<RedisCaffeineCacheManagerCustomizer> cacheManagerCustomizers,
												  ObjectProvider<ServerIdGenerator> serverIdGenerators) {
		Object serverId = cacheConfigProperties.getServerId();
		if (serverId == null || "".equals(serverId)) {
			serverIdGenerators
					.ifAvailable(serverIdGenerator -> cacheConfigProperties.setServerId(serverIdGenerator.get()));
		}
		RedisCaffeineCacheManager cacheManager = new RedisCaffeineCacheManager(cacheConfigProperties,
				stringKeyRedisTemplate);
		cacheManagerCustomizers.orderedStream().forEach(customizer -> customizer.customize(cacheManager));
		return cacheManager;
	}

	/**
	 * 可自定义名称为stringKeyRedisTemplate的RedisTemplate覆盖掉默认RedisTemplate。
	 */
	@Bean
	@ConditionalOnMissingBean(name = "stringKeyRedisTemplate")
	public RedisTemplate<Object, Object> stringKeyRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(name = "cacheMessageListenerContainer")
	public RedisMessageListenerContainer cacheMessageListenerContainer(CacheConfigProperties cacheConfigProperties,
			@Qualifier("stringKeyRedisTemplate") RedisTemplate<Object, Object> stringKeyRedisTemplate,
			@Qualifier("cacheMessageListener") CacheMessageListener cacheMessageListener) {
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer
				.setConnectionFactory(Objects.requireNonNull(stringKeyRedisTemplate.getConnectionFactory()));
		redisMessageListenerContainer.addMessageListener(cacheMessageListener,
				new ChannelTopic(cacheConfigProperties.getRedis().getTopic()));
		return redisMessageListenerContainer;
	}

	@Bean
	@SuppressWarnings("unchecked")
	@ConditionalOnMissingBean(name = "cacheMessageListener")
	public CacheMessageListener cacheMessageListener(
			@Qualifier("stringKeyRedisTemplate") RedisTemplate<Object, Object> stringKeyRedisTemplate,
			RedisCaffeineCacheManager redisCaffeineCacheManager) {
		return new CacheMessageListener((RedisSerializer<Object>) stringKeyRedisTemplate.getValueSerializer(),
				redisCaffeineCacheManager);
	}

	@Bean
	@ConditionalOnMissingBean(ServerIdGenerator.class)
	public ServerIdGenerator redisSequenceServerIdGenerator(
			@Qualifier("stringKeyRedisTemplate") RedisTemplate<Object, Object> stringKeyRedisTemplate,
			CacheConfigProperties properties) {
		return new RedisSequenceServerIdGenerator(stringKeyRedisTemplate, properties);
	}

}
