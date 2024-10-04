package com.example.vietisbaitapbuoi3.entities.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    String newPassword;
    String oldPassword;
    public ChangePasswordDTO(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
}
