package com.example.vietisbaitapbuoi3.entities.dto;

import com.example.vietisbaitapbuoi3.entities.enums.Level;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AccountRequestDTO {
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

        private Long departmentId;
}
