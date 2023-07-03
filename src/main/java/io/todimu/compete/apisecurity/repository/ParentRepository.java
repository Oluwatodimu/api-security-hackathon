package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
}
