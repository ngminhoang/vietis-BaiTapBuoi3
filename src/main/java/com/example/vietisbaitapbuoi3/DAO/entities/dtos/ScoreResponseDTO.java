package com.example.vietisbaitapbuoi3.DAO.entities.dtos;

import com.example.vietisbaitapbuoi3.DAO.entities.Score;

import java.time.LocalDate;

public class ScoreResponseDTO {
    private Long id;

    private LocalDate date;

    private Boolean type;

    private String reason;

    public ScoreResponseDTO(Score score) {
        this.id = score.getId();
        this.date = score.getDate();
        this.type = score.getType();
    }
}
