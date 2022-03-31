package com.mapsa.duolingo.level;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LevelConverter implements AttributeConverter<Level, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Level attribute) {
        return attribute.getValue();
    }

    @Override
    public Level convertToEntityAttribute(Integer dbData) {
        return Level.of(dbData);
    }
}
