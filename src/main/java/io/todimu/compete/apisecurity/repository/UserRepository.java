package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findByUserId(UUID userId);
}
