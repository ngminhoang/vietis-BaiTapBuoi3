package com.example.vietisbaitapbuoi3.DAO.entities.dtos;

import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import jakarta.persistence.Column;

public class DepartmentResponseDTO {
    private Long id;

    private String name;

    private String code;

    public DepartmentResponseDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.code = department.getCode();
    }
}
