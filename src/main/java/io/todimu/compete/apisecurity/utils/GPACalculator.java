package io.todimu.compete.apisecurity.utils;


import io.todimu.compete.apisecurity.dto.response.GPAResponse;
import io.todimu.compete.apisecurity.model.CourseRegistration;
import io.todimu.compete.apisecurity.model.Student;
import io.todimu.compete.apisecurity.service.CourseRegistrationService;
import io.todimu.compete.apisecurity.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class GPACalculator {

    private final StudentService studentService;

    private final CourseRegistrationService courseRegistrationService;

    public GPAResponse calculateStudentGpa(String matricNumber) {
        AtomicInteger courseUnitsSum = new AtomicInteger();
        AtomicReference<Float> gradePointsSum = new AtomicReference<>((float) 0);

        Student student = studentService.findStudentDboByMatricNumber(matricNumber);
        List<CourseRegistration> courseRegistration = courseRegistrationService.findAllByStudent(student);
        courseRegistration.forEach(
                registration -> {
                    int courseUnit = registration.getCourse().getUnits();
                    float scorePointEquivalent = getGradePointEquivalentFromCourseScore(registration.getCourseGrade().getGrade());
                    float gradePoints = (float) courseUnit * scorePointEquivalent;
                    courseUnitsSum.set(courseUnitsSum.get() + courseUnit);
                    gradePointsSum.set(gradePointsSum.get() + gradePoints);
                }
        );

        Float studentGPA = gradePointsSum.get() / (float) courseUnitsSum.get();

        return GPAResponse.builder()
                .name(student.getFirstName() + " " + student.getLastName())
                .matricNumber(student.getMatricNumber())
                .gpa(studentGPA)
                .build();
    }

    private int getGradePointEquivalentFromCourseScore(float score) {
        int gradePointEquivalent;

        if (score >= 70.0) {
            gradePointEquivalent = 7;
        } else if (score >= 65.0 && score < 70.0) {
            gradePointEquivalent = 6;
        } else if (score >= 60.0 && score < 65.0) {
            gradePointEquivalent = 5;
        } else if (score >= 55.0 && score < 60.0) {
            gradePointEquivalent = 4;
        } else if (score >= 50.0 && score < 55.0) {
            gradePointEquivalent = 3;
        } else if (score >= 45.0 && score < 50.0) {
            gradePointEquivalent = 2;
        } else if (score >= 40.0 && score < 45.0) {
            gradePointEquivalent = 1;
        } else {
            gradePointEquivalent = 0;
        }
        return gradePointEquivalent;
    }
}
