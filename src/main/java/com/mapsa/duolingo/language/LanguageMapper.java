package com.mapsa.duolingo.language;

import com.mapsa.duolingo.common.GenericModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper extends GenericModelMapper<Language,LanguageDto> {
}