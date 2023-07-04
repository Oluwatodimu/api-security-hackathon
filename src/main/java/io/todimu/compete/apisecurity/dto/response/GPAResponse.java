package io.todimu.compete.apisecurity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GPAResponse {

    private String name;
    private String matricNumber;
    private Float gpa;
}
