package com.mapsa.duolingo.rabbitMq;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class MessageDTO {

    private String emailAddress;

    private String message;
}
