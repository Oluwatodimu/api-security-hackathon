package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    Optional<Course> findByName(String name);

    Optional<Course> findByCode(String code);

}
