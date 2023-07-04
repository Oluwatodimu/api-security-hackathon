package io.todimu.compete.apisecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash
@NoArgsConstructor
@AllArgsConstructor
public class ActivationKey {

    private String activationKey;
    private String userId;
}
