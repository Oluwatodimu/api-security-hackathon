package io.todimu.compete.apisecurity.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.todimu.compete.apisecurity.config.properties.RedisProperties;
import io.todimu.compete.apisecurity.dto.ActivationKey;
import io.todimu.compete.apisecurity.repository.ActivationKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class ActivationKeyRepositoryImpl implements ActivationKeyRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    private final RedisProperties redisProperties;

    private final ObjectMapper objectMapper;

    @Override
    public ActivationKey save(ActivationKey key) {
        try {

            String activationKeyJson = objectMapper.writeValueAsString(key);
            redisTemplate.opsForValue().set(key.getActivationKey(), activationKeyJson);
            redisTemplate.expire(key.getActivationKey(), redisProperties.getTimeToLive(), TimeUnit.MINUTES);
            return key;

        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }

    }

    @Override
    public ActivationKey findByActivationKey(String activationKey) {
        try {

            String activationKeyJson = (String) redisTemplate.opsForValue().get(activationKey);
            return objectMapper.readValue(activationKeyJson, ActivationKey.class);

        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
