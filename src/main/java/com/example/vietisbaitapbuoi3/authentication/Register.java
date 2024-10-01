package com.example.vietisbaitapbuoi3.authentication;

import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Register {
    private String mail;
    private String password;
    private String name;
    private Boolean gender;
    private String imgPath;
    private LocalDate birthday;
    private Integer salary;
    private Level level;
    private String phoneNumber;
    private String note;
    private Role role;
}
