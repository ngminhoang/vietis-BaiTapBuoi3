package com.example.vietisbaitapbuoi3.entities.dto;

import lombok.Getter;

@Getter
public class AccountScoreCountInforDTO {
    private Long accountId;
    private String accountName;
    private String imgPath;
    private String departmentName;

    public AccountScoreCountInforDTO(Long accountId, String accountName, String imgPath, String departmentName) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.imgPath = imgPath;
        this.departmentName = departmentName;
    }
}
