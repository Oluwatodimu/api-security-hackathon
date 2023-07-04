package io.todimu.compete.apisecurity.mapper;

import io.todimu.compete.apisecurity.dto.TeacherDto;
import io.todimu.compete.apisecurity.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDto, Teacher> {
}
