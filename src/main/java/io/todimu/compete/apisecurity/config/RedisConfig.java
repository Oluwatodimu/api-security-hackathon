package io.todimu.compete.apisecurity.config;

import io.todimu.compete.apisecurity.config.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(redisProperties.getHost());
        redisConfiguration.setPort(redisProperties.getPort());
        redisConfiguration.setDatabase(redisProperties.getDatabase());
        redisConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(Long.parseLong(redisProperties.getTimeout())));
        return new JedisConnectionFactory(redisConfiguration, jedisClientConfiguration.build());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}
