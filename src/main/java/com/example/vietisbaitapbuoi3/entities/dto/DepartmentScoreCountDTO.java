package com.example.vietisbaitapbuoi3.entities.dto;

import lombok.Data;

@Data
public class DepartmentScoreCountDTO {
    private Long departmentId;
    private String departmentName;
    private Long countTrue;
    private Long countFalse;

    public DepartmentScoreCountDTO(Long departmentId, String departmentName, Long countTrue, Long countFalse) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.countTrue = countTrue;
        this.countFalse = countFalse;
    }
}
