package io.todimu.compete.apisecurity.mapper;

import io.todimu.compete.apisecurity.dto.SemesterDto;
import io.todimu.compete.apisecurity.model.Semester;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SemesterMapper extends EntityMapper<SemesterDto, Semester> {
}
