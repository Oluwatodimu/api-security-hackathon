package io.todimu.compete.apisecurity.service;

import io.todimu.compete.apisecurity.dto.StudentDto;
import io.todimu.compete.apisecurity.dto.TeacherDto;
import io.todimu.compete.apisecurity.dto.request.CourseRegistrationRequest;
import io.todimu.compete.apisecurity.dto.response.StudentsRegisteredForCourseResponse;
import io.todimu.compete.apisecurity.enums.CourseRegistrationStatus;
import io.todimu.compete.apisecurity.exception.CourseAlreadyRegisteredException;
import io.todimu.compete.apisecurity.model.Course;
import io.todimu.compete.apisecurity.model.CourseRegistration;
import io.todimu.compete.apisecurity.model.Student;
import io.todimu.compete.apisecurity.repository.CourseRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final CourseService courseService;

    private CourseGradeService courseGradeService;

    private final CourseRegistrationRepository courseRegistrationRepository;

    public void setCourseGradeService(CourseGradeService courseGradeService) {
        this.courseGradeService = courseGradeService;
    }

    public List<UUID> registerCourses(CourseRegistrationRequest registrationRequest) {
        Student student = studentService.findStudentDboByMatricNumber(registrationRequest.getMatricNumber());
        List<Course> courseList = getCoursesFromRequest(registrationRequest.getCourseCodes());
        ensureCoursesAreNotRegisteredMoreThanOnce(courseList, student);

        return courseList.stream()
                .map(course -> {
                    CourseRegistration courseRegistration = createCourseRegistrationDbo(student, course);
                    courseRegistrationRepository.save(courseRegistration);
                    courseGradeService.createCourseGrade(courseRegistration);
                    return courseRegistration.getId();
                })
                .collect(Collectors.toList());
    }

    private List<Course> getCoursesFromRequest(Set<String> courseCodes) {
        return courseCodes.stream()
                .map(courseService::findCourseByCourseCode)
                .collect(Collectors.toList());
    }

    private void ensureCoursesAreNotRegisteredMoreThanOnce(List<Course> courseList, Student student) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByStudent(student);
        List<Course> registeredCourses = courseService.getCoursesById(courseRegistrations);
        boolean hasIntersection = hasIntersection(courseList, registeredCourses);

        if (hasIntersection) {
            throw new CourseAlreadyRegisteredException("one or more courses already registered");
        }
    }

    private boolean hasIntersection(List<Course> unregisteredCourses, List<Course> registeredCourses) {
        boolean hasIntersection = false;
        Set<Course> registeredCourseSet = new HashSet<>(registeredCourses);

        for (Course course: unregisteredCourses) {
            if (registeredCourseSet.contains(course)) {
                hasIntersection = true;
                break;
            }
        }
        return hasIntersection;
    }

    private CourseRegistration createCourseRegistrationDbo(Student student, Course course) {
        return CourseRegistration.builder()
                .course(course)
                .student(student)
                .registrationStatus(CourseRegistrationStatus.REGISTERED)
                .build();
    }

    public StudentsRegisteredForCourseResponse findStudentsRegisteredForCourse(String courseCode) {
        Course course = courseService.findCourseByCourseCode(courseCode);
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByCourse(course);
        Set<StudentDto> registeredStudents = studentService.getStudentsFromCourseRegistration(courseRegistrations);
        Set<TeacherDto> teacherDtos = teacherService.getCourseTeachersForCourse(course);

        return StudentsRegisteredForCourseResponse.builder()
                .courseCode(course.getCode())
                .courseName(course.getName())
                .courseTeachers(teacherDtos)
                .registeredStudents(registeredStudents)
                .build();
    }

    public CourseRegistration findByCourseAndStudent(Course course, Student student) {
        return courseRegistrationRepository.findByCourseAndStudent(course, student)
                .orElseThrow(() -> new RuntimeException("course registration not found"));
    }

    public List<CourseRegistration> findAllByStudent(Student student) {
        return courseRegistrationRepository.findAllByStudent(student);
    }
}
