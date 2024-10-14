package com.example.vietisbaitapbuoi3.DAO.entities.enums;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    LEVEL_6(6),
    LEVEL_7(7),
    LEVEL_8(8),
    LEVEL_9(9),
    LEVEL_10(10);
    private final int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Level fromValue(int value) {
        for (Level level : Level.values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid level value: " + value);
    }

    public static List<String> AllValue() {
        return Arrays.stream(Level.values())
                .map(Level::name)
                .toList();
    }
}
