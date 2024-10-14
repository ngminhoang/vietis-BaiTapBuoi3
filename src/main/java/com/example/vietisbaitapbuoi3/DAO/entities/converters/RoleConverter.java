package com.example.vietisbaitapbuoi3.DAO.entities.converters;

import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Role.fromValue(dbData);
    }
}

