package io.todimu.compete.apisecurity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {

    @NonNull
    @NotEmpty
    private String firstName;

    @NonNull
    @NotEmpty
    private String lastName;

    @NonNull
    @Pattern(regexp = "^\\d{13}$")
    private String phoneNumber;

    @Email
    @NonNull
    private String email;

    @NonNull
    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$")
    private String password;
}
