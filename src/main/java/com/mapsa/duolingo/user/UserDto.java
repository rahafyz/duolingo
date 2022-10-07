package com.mapsa.duolingo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Default;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    @JsonIgnore
    private String password;

    private String emailAddress;

    private Level level;

}
