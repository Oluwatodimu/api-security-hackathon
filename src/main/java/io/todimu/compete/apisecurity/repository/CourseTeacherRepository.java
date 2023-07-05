package io.todimu.compete.apisecurity.repository;


import io.todimu.compete.apisecurity.model.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, UUID> {
}
