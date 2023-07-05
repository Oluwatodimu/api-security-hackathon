package io.todimu.compete.apisecurity.dto;

import io.todimu.compete.apisecurity.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StudentStatus studentStatus;
    private String matricNumber;
}
