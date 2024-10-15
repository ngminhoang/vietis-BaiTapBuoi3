package com.example.vietisbaitapbuoi3.DAO.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department {

    @Id // Dùng Jakarta Persistence cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonIgnore
    private List<Account> accounts;
}
