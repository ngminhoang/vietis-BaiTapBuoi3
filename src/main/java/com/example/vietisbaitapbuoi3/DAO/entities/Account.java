package com.example.vietisbaitapbuoi3.DAO.entities;

import com.example.vietisbaitapbuoi3.DAO.entities.converters.LevelConverter;
import com.example.vietisbaitapbuoi3.DAO.entities.converters.RoleConverter;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import com.example.vietisbaitapbuoi3.utils.UserUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;
    private Boolean gender;

    private String imgPath;

    private LocalDate birthday;

    private Integer salary;

    @Convert(converter = LevelConverter.class)
    private Level level;

    private String mail;

    @Column(length = 11)
    private String phoneNumber;

    private String note;

    @Convert(converter = RoleConverter.class)
    private Role role;

    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonIgnore
    private List<Score> scores;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    public Account(AccountRequestDTO accountDTO) {
        this.id = accountDTO.getId();
        this.code = accountDTO.getCode();
        this.name = accountDTO.getName();
        this.gender = accountDTO.getGender();
        this.imgPath = accountDTO.getImgPath();
        this.birthday = accountDTO.getBirthday();
        this.salary = accountDTO.getSalary();
        this.level = accountDTO.getLevel();
        this.mail = accountDTO.getMail();
        this.phoneNumber = accountDTO.getPhoneNumber();
        this.note = accountDTO.getNote();
        try{
            this.department = UserUtil.getDepartmentById(accountDTO.getDepartmentId());
        }
        catch (Exception e){}
    }

    @Override
    public String getUsername() {
        return this.mail;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
