package com.mapsa.duolingo.user;

import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    @ApiModelProperty(required = false, hidden = true)
    private final Long id;

    @ApiModelProperty(required = false, hidden = false)
    private final String firstName;

    @ApiModelProperty(required = true, hidden = true)
    private final String lastName;

    @ApiModelProperty(required = true)
    private final String userName;

    @ApiModelProperty(required = true)
    private final String emailAddress;

    private final Level level;
}
