package com.example.vietisbaitapbuoi3.DAO.entities.dtos;

import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import lombok.Data;

import java.time.LocalDate;

@Data
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
