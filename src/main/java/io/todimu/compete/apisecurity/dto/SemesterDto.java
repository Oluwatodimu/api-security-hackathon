package io.todimu.compete.apisecurity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SemesterDto {

    private Date startDate;
    private Date endDate;
}
