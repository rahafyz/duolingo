package com.mapsa.duolingo.user;

import com.mapsa.duolingo.level.Level;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final String userName;
    @NotNull
    private final String emailAddress;
    private final Level level;
}
