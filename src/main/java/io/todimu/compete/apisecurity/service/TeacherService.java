package io.todimu.compete.apisecurity.service;

import io.todimu.compete.apisecurity.dto.TeacherDto;
import io.todimu.compete.apisecurity.enums.TeacherStatus;
import io.todimu.compete.apisecurity.exception.UserNotFoundException;
import io.todimu.compete.apisecurity.mapper.TeacherMapper;
import io.todimu.compete.apisecurity.model.Course;
import io.todimu.compete.apisecurity.model.CourseTeacher;
import io.todimu.compete.apisecurity.model.Teacher;
import io.todimu.compete.apisecurity.model.User;
import io.todimu.compete.apisecurity.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherDto registerTeacher(User user) {
        Teacher teacher = createTeacher(user);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    private Teacher createTeacher(User user) {
        return Teacher.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .teacherStatus(TeacherStatus.ACTIVE)
                .build();
    }

    public Teacher findTeacherDboByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public Set<TeacherDto> getCourseTeachersForCourse(Course course) {
        Set<CourseTeacher> courseTeachers = course.getCourseTeachers();
        return courseTeachers.stream()
                .map(CourseTeacher::getTeacher)
                .map(teacherMapper::toDto)
                .collect(Collectors.toSet());
    }
}
