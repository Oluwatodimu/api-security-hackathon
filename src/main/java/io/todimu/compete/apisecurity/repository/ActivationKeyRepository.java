package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.dto.ActivationKey;

public interface ActivationKeyRepository {

    ActivationKey save(ActivationKey key);

    ActivationKey findByActivationKey(String activationKey);
}
