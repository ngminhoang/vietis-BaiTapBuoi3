package com.example.vietisbaitapbuoi3.DAO.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO {
    String newPassword;
    String oldPassword;
}
