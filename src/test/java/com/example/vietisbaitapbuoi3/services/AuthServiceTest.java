package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.authentication.AuthenticationRequest;
import com.example.vietisbaitapbuoi3.authentication.AuthenticationResponse;
import com.example.vietisbaitapbuoi3.authentication.Register;
import com.example.vietisbaitapbuoi3.configuration.JwtService;
import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
        AuthenticationRequest request = new AuthenticationRequest(account.getMail(), account.getPassword());
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.of(account));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(account)).thenReturn("token");
        AuthenticationResponse response = authService.login(request);
        assertNotNull(response);
        assertEquals("token", response.getToken());
        verify(jwtService, times(1)).generateToken(account);
    }

    @Test
    public void testLogin_Failure_NotFoundEmail() {
        AuthenticationRequest request = new AuthenticationRequest(account.getMail(), account.getPassword());
        when(accountRepository.findByMail(anyString())).thenReturn(null);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(account)).thenReturn("token");
        AuthenticationResponse response = authService.login(request);
        assertNull(response);
        verify(jwtService, never()).generateToken(account);
    }

    @Test
    public void testLogin_Failure_ErrorPassword() {
        AuthenticationRequest request = new AuthenticationRequest(account.getMail(), account.getPassword());
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.of(account));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(jwtService.generateToken(account)).thenReturn("token");
        AuthenticationResponse response = authService.login(request);
        assertNull(response);
        verify(jwtService, never()).generateToken(account);
    }


    @Test
    public void testRegister_Success() {
        Register request = new Register(account);
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn(account.getPassword());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(jwtService.generateToken(any(Account.class))).thenReturn("token");

        AuthenticationResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals("token", response.getToken());
        verify(accountRepository, times(1)).findByMail(request.getMail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    public void testRegister_Failure_MailExisted() {
        Register request = new Register(account);
        when(accountRepository.findByMail(anyString())).thenReturn(Optional.of(account));
        when(passwordEncoder.encode(request.getPassword())).thenReturn(account.getPassword());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(jwtService.generateToken(any(Account.class))).thenReturn("token");

        AuthenticationResponse response = authService.register(request);

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
