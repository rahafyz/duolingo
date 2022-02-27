package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.common.GenericModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CUMapper extends GenericModelMapper<CourseUser, CourseUserDto> {
}
