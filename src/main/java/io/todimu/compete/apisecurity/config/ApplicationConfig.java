package io.todimu.compete.apisecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.todimu.compete.apisecurity.config.properties.RateLimiterProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final RateLimiterProperties rateLimiterProperties;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RateLimiter rateLimiter() {
        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom()
                .limitForPeriod(rateLimiterProperties.getMaxNumberOfRequests())
                .limitRefreshPeriod(Duration.ofMinutes(rateLimiterProperties.getTimePeriodForRateLimit()))
                .build();

        return RateLimiter.of("api-security-rate-limiter", rateLimiterConfig);
    }
}
