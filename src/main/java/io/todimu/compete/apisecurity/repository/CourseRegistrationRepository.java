package io.todimu.compete.apisecurity.repository;


import io.todimu.compete.apisecurity.model.Course;
import io.todimu.compete.apisecurity.model.CourseRegistration;
import io.todimu.compete.apisecurity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, UUID> {

    List<CourseRegistration> findAllByStudent(Student student);

    List<CourseRegistration> findAllByCourse(Course course);

    Optional<CourseRegistration> findByCourseAndStudent(Course course, Student student);
}
