package io.todimu.compete.apisecurity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentGrades {

    private String courseCode;
    private String courseName;
    private Float studentGrade;
}
