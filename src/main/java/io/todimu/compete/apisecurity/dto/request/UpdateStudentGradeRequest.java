package io.todimu.compete.apisecurity.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentGradeRequest {

    @NonNull
    @NotEmpty
    @Pattern(regexp = "\\d{6}")
    private String matricNumber;

    @NonNull
    private Map<String, Float> courseScores;
}
