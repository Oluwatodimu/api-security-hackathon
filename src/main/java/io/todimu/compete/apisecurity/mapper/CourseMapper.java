package io.todimu.compete.apisecurity.mapper;


import io.todimu.compete.apisecurity.dto.CourseDto;
import io.todimu.compete.apisecurity.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDto, Course> {

}
