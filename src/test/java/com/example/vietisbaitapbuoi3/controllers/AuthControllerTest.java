package com.example.vietisbaitapbuoi3.controllers;

import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.RegisterDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import com.example.vietisbaitapbuoi3.services.AuthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthServiceImpl authService;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthenticationRequestDTO authenticationRequestDTO;
    private AuthenticationResponseDTO authenticationResponse;
    private RegisterDTO registerDTORequest;
    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationRequestDTO = new AuthenticationRequestDTO("user@example.com", "password");
        authenticationResponse = new AuthenticationResponseDTO("token");
        registerDTORequest = RegisterDTO.builder().mail("test@example.com").password("password123").name("Test User").gender(true).imgPath("/images/test.jpg").birthday(LocalDate.of(1990, 1, 1)).salary(50000).level(Level.LEVEL_1).phoneNumber("123-456-7890").note("This is a test user").role(Role.ROLE_EMPLOYEE).build();
        account = Account.builder().birthday(LocalDate.of(12, 12, 12)).id(1L).role(Role.ROLE_EMPLOYEE).name("Hoàng Minh Nguyễn").password("123456").level(Level.LEVEL_1).gender(true).mail("hoang8ctt@gmail.com").build();
    }

    @Test
    public void testLogin_Success() throws Exception {
        when(authService.login(any(AuthenticationRequestDTO.class))).thenReturn(authenticationResponse);

        mockMvc.perform(post("/api/public/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authenticationRequestDTO))).andExpect(status().isOk()).andExpect(jsonPath("$.token").value("token"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        when(authService.login(any(AuthenticationRequestDTO.class))).thenReturn(null);

        mockMvc.perform(post("/api/public/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authenticationRequestDTO))).andExpect(status().isBadRequest());
    }

    @Test
    public void testRegister_Success() throws Exception {
        when(authService.register(any(RegisterDTO.class))).thenReturn(authenticationResponse);

        mockMvc.perform(post("/api/public/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registerDTORequest))).andExpect(status().isOk()).andExpect(jsonPath("$.token").value("token"));
    }

    @Test
    public void testRegister_Failure() throws Exception {
        when(authService.register(any(RegisterDTO.class))).thenReturn(null);

        mockMvc.perform(post("/api/public/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registerDTORequest))).andExpect(status().isBadRequest());
    }

    @Test
    public void testAuthenAdmin_Success() throws Exception {
        when(authService.authenAdmin(any(Account.class))).thenReturn(ResponseEntity.ok(true));

        mockMvc.perform(post("/api/public/authen_admin").principal(
                () -> account.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenAdmin_Failure() throws Exception {
        when(authService.authenAdmin(any(Account.class))).thenReturn(ResponseEntity.ok(false));

        mockMvc.perform(post("/api/public/authen_admin").principal(() -> account.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenEmployee_Success() throws Exception {
        when(authService.authenEmployee(any(Account.class))).thenReturn(ResponseEntity.ok(true));

        mockMvc.perform(post("/api/public/authen_employee").principal(() -> account.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenEmployee_Failure() throws Exception {
        when(authService.authenEmployee(any(Account.class))).thenReturn(ResponseEntity.ok(false));

        mockMvc.perform(post("/api/public/authen_employee").principal(() -> account.getUsername()))
                .andExpect(status().isOk());
    }
}
