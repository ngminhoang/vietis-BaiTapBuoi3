package com.example.vietisbaitapbuoi3.authentication;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Register(Account account) {
        this.mail = account.getMail();
        this.password = account.getPassword();
        this.name = account.getName();
        this.gender = account.getGender();
        this.imgPath = account.getImgPath();
        this.salary = account.getSalary();
        this.level = account.getLevel();
        this.phoneNumber = account.getPhoneNumber();
        this.note = account.getNote();
        this.role = account.getRole();
        this.birthday = account.getBirthday();
    }
}
