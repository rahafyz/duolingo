package com.mapsa.duolingo.level;


import java.util.Arrays;
import java.util.Objects;

public enum Level {
    BEGINNER(0), ELEMENTARY(1), PRE_INTERMEDIATE(2), INTERMEDIATE(3), UPPER_INTERMEDIATE(4), ADVANCED(5);

    private Integer value;

    Level(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Level of(Integer value) {
        return Arrays.stream(Level.values()).filter(size -> Objects.equals(size.getValue(), value))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException();
                });
    }
}
