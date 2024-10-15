package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.RegisterDTO;
import com.example.vietisbaitapbuoi3.configuration.JwtService;
import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
    public void testLogin_Success() {
        AuthenticationRequestDTO request = new AuthenticationRequestDTO(account.getMail(), account.getPassword());
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.of(account));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(account)).thenReturn("token");
        AuthenticationResponseDTO response = authService.login(request);
        assertNotNull(response);
        assertEquals("token", response.getToken());
        verify(jwtService, times(1)).generateToken(account);
    }

    @Test
    public void testLogin_Failure_NotFoundEmail() {
        AuthenticationRequestDTO request = new AuthenticationRequestDTO(account.getMail(), account.getPassword());
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(account)).thenReturn("token");
        AuthenticationResponseDTO response = authService.login(request);
        verify(jwtService, never()).generateToken(account);
    }

    @Test
    public void testLogin_Failure_ErrorPassword() {
        AuthenticationRequestDTO request = new AuthenticationRequestDTO(account.getMail(), account.getPassword());
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.of(account));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(jwtService.generateToken(account)).thenReturn("token");
        AuthenticationResponseDTO response = authService.login(request);
        verify(jwtService, never()).generateToken(account);
    }


    @Test
    public void testRegister_Success() {
        RegisterDTO request = new RegisterDTO(account);
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn(account.getPassword());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(jwtService.generateToken(any(Account.class))).thenReturn("token");

        AuthenticationResponseDTO response = authService.register(request);

        assertNotNull(response);
        assertEquals("token", response.getToken());
        verify(accountRepository, times(1)).findByMail(request.getMail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    public void testRegister_Failure_MailExisted() {
        RegisterDTO request = new RegisterDTO(account);
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.of(account));
        when(passwordEncoder.encode(request.getPassword())).thenReturn(account.getPassword());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(jwtService.generateToken(any(Account.class))).thenReturn("token");

        AuthenticationResponseDTO response = authService.register(request);

        assertNull(response);
        verify(accountRepository, times(1)).findByMail(request.getMail());
        verify(passwordEncoder, never()).encode(request.getPassword());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void testAuthenAdmin_Success() {
        account.setRole(Role.ROLE_ADMIN);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        ResponseEntity<Boolean> response = authService.authenAdmin(account);

        assertNotNull(response);
        assertTrue(response.getBody());
    }

    @Test
    public void testauthenEmployee_Success() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        ResponseEntity<Boolean> response = authService.authenEmployee(account);

        assertNotNull(response);
        assertTrue(response.getBody());
    }

    @Test
    public void testAuthenAdmin_FailureToken() {
        account.setRole(Role.ROLE_ADMIN);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Boolean> response = authService.authenAdmin(account);

        assertNotNull(response);
        assertFalse(response.getBody());
    }

    @Test
    public void testauthenEmployee_FailureToken() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Boolean> response = authService.authenEmployee(account);

        assertNotNull(response);
        assertFalse(response.getBody());
    }

    @Test
    public void testAuthenAdmin_FailureRole() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        ResponseEntity<Boolean> response = authService.authenAdmin(account);

        assertNotNull(response);
        assertFalse(response.getBody());
    }

    @Test
    public void testauthenEmployee_FailureRole() {
        account.setRole(Role.ROLE_ADMIN);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        ResponseEntity<Boolean> response = authService.authenEmployee(account);

        assertNotNull(response);
        assertFalse(response.getBody());
    }

}
