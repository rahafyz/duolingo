package com.mapsa.duolingo.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLoginMapper {
    @Mapping(target = "firstName" , ignore = true)
    @Mapping(target = "lastName" , ignore = true)
    @Mapping(target = "emailAddress" , ignore = true)
    @Mapping(target = "level" , ignore = true)
    public User toEntity(UserDto userDTO);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "firstName" , ignore = true)
    @Mapping(target = "lastName" , ignore = true)
    @Mapping(target = "emailAddress" , ignore = true)
    @Mapping(target = "level" , ignore = true)
    public UserDto toDto(User user);
}
