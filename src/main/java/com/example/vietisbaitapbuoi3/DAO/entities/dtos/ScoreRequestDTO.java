package com.example.vietisbaitapbuoi3.DAO.entities.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ScoreRequestDTO {
    private Long id;

    private LocalDate date;

    private Boolean type;

    private String reason;

    private Long accountId;
}
