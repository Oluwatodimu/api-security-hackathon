package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

}
