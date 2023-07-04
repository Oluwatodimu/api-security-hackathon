package io.todimu.compete.apisecurity.service;


import io.todimu.compete.apisecurity.dto.StudentDto;
import io.todimu.compete.apisecurity.dto.request.AddParentRequest;
import io.todimu.compete.apisecurity.dto.request.UpdateStudentRequest;
import io.todimu.compete.apisecurity.dto.response.GetStudentGradesResponse;
import io.todimu.compete.apisecurity.dto.response.StudentGrades;
import io.todimu.compete.apisecurity.enums.StudentStatus;
import io.todimu.compete.apisecurity.exception.UserNotFoundException;
import io.todimu.compete.apisecurity.mapper.StudentMapper;
import io.todimu.compete.apisecurity.model.CourseRegistration;
import io.todimu.compete.apisecurity.model.Parent;
import io.todimu.compete.apisecurity.model.Student;
import io.todimu.compete.apisecurity.model.User;
import io.todimu.compete.apisecurity.repository.StudentRepository;
import io.todimu.compete.apisecurity.utils.ValueGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final ParentService parentService;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StudentDto registerStudent(User user) {
        Student student = createStudent(user);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    private Student createStudent(User user) {
        return Student.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .matricNumber(ValueGenerator.generateMatricNumber())
                .studentStatus(StudentStatus.ACTIVE)
                .build();
    }

    public StudentDto getStudentDetails(String emailOrMatricNumber) { // emailOrMatricNumber can be email or string
        StudentDto studentDto = null;

        if (emailOrMatricNumber.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
            studentDto = findByEmail(emailOrMatricNumber);
        } else if (emailOrMatricNumber.matches("\\d{6}")) {
            studentDto = findByMatricNumber(emailOrMatricNumber);
        }
        return studentDto;
    }

    private StudentDto findByMatricNumber(String matricNumber) {
        return studentRepository.findByMatricNumber(matricNumber)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("student not found"));
    }

    public Student findStudentDboByMatricNumber(String matricNumber) {
        return studentRepository.findByMatricNumber(matricNumber)
                .orElseThrow(() -> new UserNotFoundException("student not found"));
    }

    private StudentDto findByEmail(String email) {
        return studentRepository.findByEmailIgnoreCase(email)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("student not found"));
    }

    public Page<StudentDto> findAllStudents(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        return new PageImpl<>(students.getContent()
                        .stream()
                        .map(studentMapper::toDto)
                        .collect(Collectors.toList()));
    }

    public StudentDto updateStudentDetails(UpdateStudentRequest updateRequest) {
        Student studentToUpdate = studentRepository.findByEmailIgnoreCase(updateRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        studentToUpdate.setFirstName(updateRequest.getFirstName());
        studentToUpdate.setLastName(updateRequest.getLastName());
        userService.updateStudentUserDetails(updateRequest);
        studentRepository.save(studentToUpdate);
        return studentMapper.toDto(studentToUpdate);
    }

    public Set<StudentDto> getStudentsFromCourseRegistration(List<CourseRegistration> courseRegistrations) {
        return courseRegistrations.stream()
                .map(CourseRegistration::getStudent)
                .map(studentMapper::toDto)
                .collect(Collectors.toSet());
    }

    public GetStudentGradesResponse getStudentGrades(String matricNumber) {
        List<StudentGrades> studentGrades = new ArrayList<>();
        Student student = findStudentDboByMatricNumber(matricNumber);
        Set<CourseRegistration> courseRegistrations = student.getCourseRegistrations();
        courseRegistrations.forEach(courseRegistration -> {
                    StudentGrades grades = StudentGrades.builder()
                            .courseName(courseRegistration.getCourse().getName())
                            .courseCode(courseRegistration.getCourse().getCode())
                            .studentGrade(courseRegistration.getCourseGrade().getGrade())
                            .build();
                    studentGrades.add(grades);
                });

        return GetStudentGradesResponse.builder()
                .studentName(student.getFirstName() + " " + student.getLastName())
                .matricNumber(student.getMatricNumber())
                .studentGradesList(studentGrades)
                .build();
    }

    public Set<Parent> addParentData(AddParentRequest addParentRequest) {
        Student student = findStudentDboByMatricNumber(addParentRequest.getMatricNumber());
        parentService.addParentsData(addParentRequest.getParents(), student);
        return student.getParents();
    }

    public Set<Parent> getStudentParents(String matricNumber) {
        Student student = findStudentDboByMatricNumber(matricNumber);
        return student.getParents();
    }
}
