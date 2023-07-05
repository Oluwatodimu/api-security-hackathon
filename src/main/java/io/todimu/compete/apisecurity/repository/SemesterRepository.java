package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, UUID> {

    Optional<Semester> findById(Long id);

}
