package io.todimu.compete.apisecurity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentGradesResponse {

    private String studentName;
    private String matricNumber;
    private List<StudentGrades> studentGradesList;
}
