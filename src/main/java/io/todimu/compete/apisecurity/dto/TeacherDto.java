package io.todimu.compete.apisecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
