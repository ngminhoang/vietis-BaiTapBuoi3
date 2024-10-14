package com.example.vietisbaitapbuoi3.DAO.entities.enums;

public enum Role {
    ROLE_ADMIN(1),
    ROLE_MANAGER(2),
    ROLE_EMPLOYEE(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role fromValue(int value) {
        for (Role role : Role.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }
}

