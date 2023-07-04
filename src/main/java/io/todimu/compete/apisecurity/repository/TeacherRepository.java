package io.todimu.compete.apisecurity.repository;

import io.todimu.compete.apisecurity.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByPhoneNumber(String phoneNumber);

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
