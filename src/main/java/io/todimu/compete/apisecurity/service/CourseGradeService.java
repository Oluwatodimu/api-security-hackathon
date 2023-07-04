package io.todimu.compete.apisecurity.service;

import io.todimu.compete.apisecurity.dto.request.UpdateStudentGradeRequest;
import io.todimu.compete.apisecurity.model.Course;
import io.todimu.compete.apisecurity.model.CourseGrade;
import io.todimu.compete.apisecurity.model.CourseRegistration;
import io.todimu.compete.apisecurity.model.Student;
import io.todimu.compete.apisecurity.repository.CourseGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CourseGradeService {

    private final StudentService studentService;

    private final CourseRegistrationService courseRegistrationService;

    private final CourseService courseService;

    private final CourseGradeRepository courseGradeRepository;

    @PostConstruct
    public void init() {
        courseRegistrationService.setCourseGradeService(this);
    }

    public void createCourseGrade(CourseRegistration courseRegistration) {
        CourseGrade courseGrade = CourseGrade.builder()
                .grade((float) 0)
                .courseRegistration(courseRegistration)
                .build();
        courseGradeRepository.save(courseGrade);
    }

    @Transactional
    public void updateStudentGrade(UpdateStudentGradeRequest updateStudentGradeRequest) {
        Student student = studentService.findStudentDboByMatricNumber(updateStudentGradeRequest.getMatricNumber());
        updateStudentGradeRequest.getCourseScores().forEach(
                ((courseCode, courseGrade) -> {
                    Course existingCourse = courseService.findCourseByCourseCode(courseCode);
                    CourseRegistration courseRegistration = courseRegistrationService.findByCourseAndStudent(existingCourse, student);
                    CourseGrade grade = courseRegistration.getCourseGrade();
                    grade.setGrade(courseGrade);
                    courseGradeRepository.save(grade);
                })
        );
    }
}
