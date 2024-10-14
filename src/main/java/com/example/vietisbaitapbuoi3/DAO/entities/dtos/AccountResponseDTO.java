package com.example.vietisbaitapbuoi3.DAO.entities.dtos;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.converters.LevelConverter;
import com.example.vietisbaitapbuoi3.DAO.entities.converters.RoleConverter;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountResponseDTO {
    private Long id;

    private String code;

    private String name;
    private Boolean gender;

    private String imgPath;

    private LocalDate birthday;

    private Integer salary;

    private Level level;

    private String mail;

    private String phoneNumber;

    private String note;

    private Role role;

    public AccountResponseDTO(Account account) {
        this.id = account.getId();
        this.code = account.getCode();
        this.name = account.getName();
        this.gender = account.getGender();
        this.imgPath = account.getImgPath();
        this.birthday = account.getBirthday();
        this.salary = account.getSalary();
        this.level = account.getLevel();
        this.mail = account.getMail();
        this.phoneNumber = account.getPhoneNumber();
        this.note = account.getNote();
        this.role = account.getRole();
    }
}
