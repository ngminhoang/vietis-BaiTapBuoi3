package com.example.vietisbaitapbuoi3.entities;

import com.example.vietisbaitapbuoi3.entities.converters.LevelConverter;
import com.example.vietisbaitapbuoi3.entities.converters.RoleConverter;
import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToMany(mappedBy = "score")
    private List<Score> scores;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String roleName = role.name();
//        return List.of(new SimpleGrantedAuthority(roleName));
//    }

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
