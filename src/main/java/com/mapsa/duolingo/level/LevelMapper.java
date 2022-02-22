package com.mapsa.duolingo.level;

import com.mapsa.duolingo.common.GenericModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelMapper extends GenericModelMapper<Level, LevelDto> {
}
