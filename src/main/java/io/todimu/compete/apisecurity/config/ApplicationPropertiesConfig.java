package io.todimu.compete.apisecurity.config;

import io.todimu.compete.apisecurity.config.properties.RateLimiterProperties;
import io.todimu.compete.apisecurity.config.properties.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "redis", ignoreUnknownFields = false)
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "rate-limiter", ignoreUnknownFields = false)
    public RateLimiterProperties rateLimiterProperties() {
        return new RateLimiterProperties();
    }
}
