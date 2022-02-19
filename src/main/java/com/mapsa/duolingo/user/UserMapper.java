package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericModelMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper{
    User toEntity(UserDTO UserDto);
    UserDTO toDTO(User user);
}
