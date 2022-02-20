package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.GenericModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends GenericModelMapper<Course,CourseDto> {
}
