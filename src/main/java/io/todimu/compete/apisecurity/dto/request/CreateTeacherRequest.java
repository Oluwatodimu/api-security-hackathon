package io.todimu.compete.apisecurity.dto.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class CreateTeacherRequest {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    @Pattern(regexp = "\\d{13}")
    private String phoneNumber;

    @Email
    @NonNull
    private String email;
}
