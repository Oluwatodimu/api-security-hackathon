package io.todimu.compete.apisecurity.dto.response;


import io.todimu.compete.apisecurity.dto.StudentDto;
import io.todimu.compete.apisecurity.dto.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentsRegisteredForCourseResponse {

    private String courseCode;

    private String courseName;

    private Set<TeacherDto> courseTeachers;

    private Set<StudentDto> registeredStudents;
}
