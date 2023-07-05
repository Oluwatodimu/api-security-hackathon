package io.todimu.compete.apisecurity.mapper;

import io.todimu.compete.apisecurity.dto.StudentDto;
import io.todimu.compete.apisecurity.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDto, Student> {
}
