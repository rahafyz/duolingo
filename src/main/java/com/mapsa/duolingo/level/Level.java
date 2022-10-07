package com.mapsa.duolingo.level;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


public enum Level implements Serializable {
    BEGINNER(0), ELEMENTARY(1), PRE_INTERMEDIATE(2), INTERMEDIATE(3), UPPER_INTERMEDIATE(4), ADVANCED(5);

    private Integer value;


    Level(@JsonProperty("level")Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


    public static Level of(Integer value){
        return Arrays.stream(Level.values()).filter(size -> Objects.equals(size.getValue(), value))
                .findFirst()
                .orElseThrow(() -> {throw new IllegalArgumentException();});
    }

}
