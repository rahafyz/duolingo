package com.mapsa.duolingo.user;

import com.mapsa.duolingo.level.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {

    @ApiModelProperty(required = false, hidden = true)
    private Long id;

    @ApiModelProperty(required = false, hidden = false)
    private String firstName;

    @ApiModelProperty(required = true, hidden = true)
    private String lastName;

    @ApiModelProperty(required = true)
    private String userName;

    @ApiModelProperty(required = true)
    private String emailAddress;

    private Level level;


}
