package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericModelMapper<User, UserDto>{
}
