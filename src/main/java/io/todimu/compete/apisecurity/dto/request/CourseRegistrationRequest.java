package io.todimu.compete.apisecurity.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class CourseRegistrationRequest {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d{6}")
    private String matricNumber;

    private String semesterName;

    @NotNull
    @NotEmpty
    private Set<String> courseCodes;
}
