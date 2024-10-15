package com.example.vietisbaitapbuoi3.controllers;


import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import com.example.vietisbaitapbuoi3.services.AccountServiceImpl;
import com.example.vietisbaitapbuoi3.utils.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private FileUtil fileUtil;

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
        List<AccountResponseDTO> accounts = Arrays.asList(new AccountResponseDTO(account));
        when(accountService.getAllAccounts()).thenReturn(ResponseEntity.ok(accounts));

        mockMvc.perform(get("/api/admin/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(accounts)));
    }


    @Test
    void testUploadImg_FileEmpty() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(true);

        mockMvc.perform(multipart("/api/employee/upload_img")
                        .file("file", file.getBytes())
                        .principal(() -> "user"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testChangePassword() throws Exception {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        Account user = account;
        when(accountService.changePassword(any(Account.class), any(ChangePasswordDTO.class))).thenReturn(ResponseEntity.ok(new AccountResponseDTO(account)));

        mockMvc.perform(post("/api/employee/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changePasswordDTO))
                        .principal(() -> user.getUsername())) // Simulating authenticated user
                .andExpect(status().isOk());
    }

    @Test
    void testGetAccountsByDepartmentCode() throws Exception {
        account.setRole(Role.ROLE_EMPLOYEE);
        List<AccountResponseDTO> accounts = List.of(new AccountResponseDTO(account));
        when(accountService.getAllAccountsByDepartment(anyString())).thenReturn(ResponseEntity.ok(accounts));

        mockMvc.perform(get("/api/admin/accounts/by-department")
                        .param("code", "HR"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(accounts)));
    }

    @Test
    void testGetAccountById() throws Exception {
        when(accountService.getAccountsById(1L)).thenReturn(ResponseEntity.ok(new AccountResponseDTO(account)));

        mockMvc.perform(get("/api/admin/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(account)));
    }

    @Test
    void testDeleteAccount() throws Exception {
        when(accountService.delete(1L)).thenReturn(ResponseEntity.ok(new AccountResponseDTO(account)));

        mockMvc.perform(delete("/api/admin/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(account)));
    }

    @Test
    void testUpdateAccount() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(account);

        Account updatedAccount = account;
        when(accountService.update(any(Account.class))).thenReturn(ResponseEntity.ok(new AccountResponseDTO(account)));

        mockMvc.perform(put("/api/admin/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new AccountResponseDTO(account))));
    }

    @Test
    void testCreateAccount() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(account);
        accountRequestDTO.setId(null);
        when(accountService.createAccount(any(Account.class))).thenReturn(ResponseEntity.ok(new AccountResponseDTO(account)));


        mockMvc.perform(post("/api/admin/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequestDTO)))
                .andExpect(status().isOk());
    }
}
