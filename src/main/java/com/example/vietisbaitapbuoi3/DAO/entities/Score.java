package com.example.vietisbaitapbuoi3.DAO.entities;

import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreRequestDTO;
import com.example.vietisbaitapbuoi3.utils.UserUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Boolean type;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Score(ScoreRequestDTO scoreRequestDTO) {
        this.date = scoreRequestDTO.getDate();
        this.type = scoreRequestDTO.getType();
        this.reason = scoreRequestDTO.getReason();
        this.id = scoreRequestDTO.getId();
        try{
            this.account = UserUtil.getAccountById(scoreRequestDTO.getAccountId());

        }
        catch (Exception e){}
    }
}

