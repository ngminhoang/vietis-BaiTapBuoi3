package com.example.vietisbaitapbuoi3.entities.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
public class AccountScoreCountDTO {
    private Long accountId;
    private String accountName;
    private Long countTrue;
    private Long countFalse;

    public AccountScoreCountDTO(Long accountId, String accountName, Long countTrue, Long countFalse) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.countTrue = countTrue;
        this.countFalse = countFalse;
    }
}
