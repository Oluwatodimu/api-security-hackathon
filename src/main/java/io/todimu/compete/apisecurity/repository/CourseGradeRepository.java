package io.todimu.compete.apisecurity.repository;


import io.todimu.compete.apisecurity.model.CourseGrade;
import io.todimu.compete.apisecurity.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseGradeRepository extends JpaRepository<CourseGrade, UUID> {

    Optional<CourseGrade> findByCourseRegistration(CourseRegistration courseRegistration);
}
