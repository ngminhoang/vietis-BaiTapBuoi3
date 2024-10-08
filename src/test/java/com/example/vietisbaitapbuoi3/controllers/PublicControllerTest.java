package com.example.vietisbaitapbuoi3.controllers;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import com.example.vietisbaitapbuoi3.services.AccountService;
import com.example.vietisbaitapbuoi3.services.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PublicControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AccountService accountService;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder()
                .birthday(LocalDate.of(12, 12, 12))
                .id(1L)
                .role(Role.ROLE_EMPLOYEE)
                .name("Hoàng Minh Nguyễn")
                .password("123456")
                .level(Level.LEVEL_1)
                .gender(true)
                .mail("hoang8ctt@gmail.com")
                .build();
    }

    @Test
    void testGetAllAccounts() throws Exception {
        List<Account> accounts = Arrays.asList(account);
        when(accountService.getAllAccounts()).thenReturn(ResponseEntity.ok(accounts));

        mockMvc.perform(get("/api/public/get_all"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(accounts)));
    }

    @Test
    void testGetAllDepartment() throws Exception {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentService.getAllDepartment()).thenReturn(ResponseEntity.ok(departments));

        mockMvc.perform(get("/api/public/department/get_all"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(departments)));
    }

    @Test
    void testGetAllLevel() throws Exception {
        List<String> levels = Arrays.asList("Level 1", "Level 2");
        when(departmentService.getAllLevel()).thenReturn(ResponseEntity.ok(levels));

        mockMvc.perform(get("/api/public/level/get_all"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(levels)));
    }

    @Test
    void testGetTopAccount() throws Exception {
        when(accountService.getTopAccount()).thenReturn(ResponseEntity.ok(List.of()));

        mockMvc.perform(get("/api/public/account/get_top"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of())));
    }
}
