package io.todimu.compete.apisecurity.dto.request;

import io.todimu.compete.apisecurity.model.Parent;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class AddParentRequest {


    @NotEmpty
    @Pattern(regexp = "\\d{6}")
    private String matricNumber;

    @NotNull
    private Set<Parent> parents;
}
