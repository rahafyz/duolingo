package com.mapsa.duolingo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapsa.duolingo.level.Level;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

//    @JsonIgnore
    private String password;

    private String emailAddress;

    private Level level;

}
