package io.todimu.compete.apisecurity.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignTeacherToCourseRequest {

    @NonNull
    @NotEmpty
    @Pattern(regexp = "\\d{3}")
    private String courseCode;

    @Email
    @NonNull
    @NotEmpty
    private String teacherEmail;
}
