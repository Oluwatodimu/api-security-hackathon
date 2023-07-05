package io.todimu.compete.apisecurity.service;


import io.todimu.compete.apisecurity.dto.request.AssignTeacherToCourseRequest;
import io.todimu.compete.apisecurity.model.Course;
import io.todimu.compete.apisecurity.model.CourseTeacher;
import io.todimu.compete.apisecurity.model.Teacher;
import io.todimu.compete.apisecurity.repository.CourseTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseTeacherService {

    private final TeacherService teacherService;

    private final CourseService courseService;

    private final CourseTeacherRepository courseTeacherRepository;

    public UUID assignTeacherToCourse(AssignTeacherToCourseRequest assignTeacherToCourseRequest) {
        Teacher teacher = teacherService.findTeacherDboByEmail(assignTeacherToCourseRequest.getTeacherEmail());
        Course course = courseService.findCourseByCourseCode(assignTeacherToCourseRequest.getCourseCode());

        CourseTeacher courseTeacher = CourseTeacher.builder()
                .course(course)
                .teacher(teacher)
                .build();
        courseTeacher = courseTeacherRepository.save(courseTeacher);
        return courseTeacher.getId();
    }
}
