package com.mapsa.duolingo.user;

import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Default;

@Data
public class UserDto {

    @ApiModelProperty(required = false, hidden = true)
    private Long id;

    @ApiModelProperty(required = false, hidden = false)
    private String firstName;

    @ApiModelProperty(required = true, hidden = true)
    private String lastName;

    @ApiModelProperty(required = true)
    private String userName;

    @ApiModelProperty(required = true,hidden = true)
    private String password;

    @ApiModelProperty(required = true)
    private String emailAddress;

    @ApiModelProperty(required = false)
    private Level level;

}
