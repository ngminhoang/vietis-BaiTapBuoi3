package com.example.vietisbaitapbuoi3.entities.dto;

import com.example.vietisbaitapbuoi3.entities.Account;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
