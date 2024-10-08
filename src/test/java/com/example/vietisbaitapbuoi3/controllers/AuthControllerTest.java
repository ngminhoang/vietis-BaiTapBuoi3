package com.example.vietisbaitapbuoi3.controllers;

import com.example.vietisbaitapbuoi3.authentication.AuthenticationRequest;
import com.example.vietisbaitapbuoi3.authentication.AuthenticationResponse;
import com.example.vietisbaitapbuoi3.authentication.Register;
import com.example.vietisbaitapbuoi3.controllers.api.AuthController;
import com.example.vietisbaitapbuoi3.controllers.api.AuthControllerImpl;
import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import com.example.vietisbaitapbuoi3.services.AuthService;
import com.example.vietisbaitapbuoi3.services.AuthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private AuthenticationRequest authenticationRequest;
    private AuthenticationResponse authenticationResponse;
    private Register registerRequest;
    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationRequest = new AuthenticationRequest("user@example.com", "password");
        authenticationResponse = new AuthenticationResponse("token");
        registerRequest = Register.builder().mail("test@example.com").password("password123").name("Test User").gender(true).imgPath("/images/test.jpg").birthday(LocalDate.of(1990, 1, 1)).salary(50000).level(Level.LEVEL_1).phoneNumber("123-456-7890").note("This is a test user").role(Role.ROLE_EMPLOYEE).build();
        account = Account.builder().birthday(LocalDate.of(12, 12, 12)).id(1L).role(Role.ROLE_EMPLOYEE).name("Hoàng Minh Nguyễn").password("123456").level(Level.LEVEL_1).gender(true).mail("hoang8ctt@gmail.com").build();
    }

    @Test
    public void testLogin_Success() throws Exception {
        when(authService.login(any(AuthenticationRequest.class))).thenReturn(authenticationResponse);

        mockMvc.perform(post("/api/public/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authenticationRequest))).andExpect(status().isOk()).andExpect(jsonPath("$.token").value("token"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        when(authService.login(any(AuthenticationRequest.class))).thenReturn(null);

        mockMvc.perform(post("/api/public/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authenticationRequest))).andExpect(status().isBadRequest());
    }

    @Test
    public void testRegister_Success() throws Exception {
        when(authService.register(any(Register.class))).thenReturn(authenticationResponse);

        mockMvc.perform(post("/api/public/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registerRequest))).andExpect(status().isOk()).andExpect(jsonPath("$.token").value("token"));
    }

    @Test
    public void testRegister_Failure() throws Exception {
        when(authService.register(any(Register.class))).thenReturn(null);

        mockMvc.perform(post("/api/public/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registerRequest))).andExpect(status().isBadRequest());
    }

    @Test
    public void testAuthenAdmin_Success() throws Exception {
        when(authService.authenAdmin(any(Account.class))).thenReturn(ResponseEntity.ok(true));

        mockMvc.perform(post("/api/public/authen_admin").principal(() -> account.getUsername())).andExpect(status().isOk()).andExpect(content().string("true"));
    }

    @Test
    public void testAuthenAdmin_Failure() throws Exception {
        when(authService.authenAdmin(any(Account.class))).thenReturn(ResponseEntity.ok(false));

        mockMvc.perform(post("/api/public/authen_admin").principal(() -> account.getUsername())).andExpect(status().isOk()).andExpect(content().string("false"));
    }

    @Test
    public void testAuthenEmployee_Success() throws Exception {
        when(authService.authenEmployee(any(Account.class))).thenReturn(ResponseEntity.ok(true));

        mockMvc.perform(post("/api/public/authen_employee").principal(() -> account.getUsername())).andExpect(status().isOk()).andExpect(content().string("true"));
    }

    @Test
    public void testAuthenEmployee_Failure() throws Exception {
        when(authService.authenEmployee(any(Account.class))).thenReturn(ResponseEntity.ok(false));

        mockMvc.perform(post("/api/public/authen_employee").principal(() -> account.getUsername())).andExpect(status().isOk()).andExpect(content().string("false"));
    }
}
