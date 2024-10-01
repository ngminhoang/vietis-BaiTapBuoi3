package com.example.vietisbaitapbuoi3.entities.converters;

import com.example.vietisbaitapbuoi3.entities.enums.Level;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LevelConverter implements AttributeConverter<Level, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Level level) {
        if (level == null) {
            return null;
        }
        return level.getValue();
    }

    @Override
    public Level convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Level.fromValue(dbData);
    }
}
