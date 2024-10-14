package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Component
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        account = Account.builder()
                .birthday(LocalDate.of(12,12,12))
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
    public void testGetTopAccount() {
        PageRequest pageable = PageRequest.of(0, 10);
        when(accountRepository.getTop10AccountsByScoreDifference(pageable)).thenReturn(List.of());
        List<AccountScoreCountInforDTO> response = accountRepository.getTop10AccountsByScoreDifference(pageable);
        assertNotNull(response);
        verify(accountRepository, times(1)).getTop10AccountsByScoreDifference(pageable);
    }

    @Test
    public void testUpdate_Success(){
        when(accountRepository.findById(any(Long.class))).thenReturn( Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        ResponseEntity<AccountResponseDTO>response = accountService.update(account);
        assertNotNull(response);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testUpdate_Failure_NotFound() {
        when(accountRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        ResponseEntity<AccountResponseDTO> response = accountService.update(account);
        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build().getStatusCode(), response.getStatusCode());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void testDelete_Success(){
        when(accountRepository.findById(any(Long.class))).thenReturn( Optional.of(account));
        ResponseEntity<AccountResponseDTO> response = accountService.delete(any(Long.class));
        verify(accountRepository, times(1)).delete(account);

    }

    @Test
    public void testDelete_Failure(){
        when(accountRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<AccountResponseDTO> response = accountService.delete(any(Long.class));
        verify(accountRepository,never()).delete(account);
    }

    @Test
    public void testCreateAccount_Success() {
        Account result = account;
        result.setId(1L);
        result.setPassword("encodedPassword");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(accountRepository.save(any(Account.class))).thenReturn(result);
        ResponseEntity<AccountResponseDTO> response = accountService.createAccount(result);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(Role.ROLE_EMPLOYEE, response.getBody().getRole());
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    public void testGetAccountsById_Success() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        ResponseEntity<AccountResponseDTO> response = accountService.getAccountsById(1L);

        assertNotNull(response);
        assertEquals(account, response.getBody());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAccountsById_Failure() {
        when(accountRepository.findById(1L)).thenReturn(null);

        ResponseEntity<AccountResponseDTO> response = accountService.getAccountsById(1L);

        assertNotNull(response);
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testChangePassword_Success() {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setOldPassword("oldPassword");
        changePasswordDTO.setNewPassword("newPassword");
        Account result = account;
        result.setId(1L);
        result.setPassword("encodedNewPassword");
        when(passwordEncoder.matches(changePasswordDTO.getOldPassword(), account.getPassword())).thenReturn(true);
        when(passwordEncoder.encode(changePasswordDTO.getNewPassword())).thenReturn("encodedNewPassword");
        when(accountRepository.save(account)).thenReturn(result);
        ResponseEntity<AccountResponseDTO> response = accountService.changePassword(account, changePasswordDTO);
        assertNotNull(response);
        assertEquals("encodedNewPassword", account.getPassword());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testChangePassword_Failure_OldPasswordMismatch() {

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setOldPassword("wrongPassword");
        changePasswordDTO.setNewPassword("newPassword");

        when(passwordEncoder.matches(changePasswordDTO.getOldPassword(), account.getPassword())).thenReturn(false);

        ResponseEntity<AccountResponseDTO> response = accountService.changePassword(account, changePasswordDTO);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of());
        ResponseEntity<List<AccountResponseDTO>> response = accountService.getAllAccounts();
        assertNotNull(response);
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllAccountsByName() {
        when(accountRepository.findAccountByName(anyString())).thenReturn(List.of());
        ResponseEntity<List<AccountResponseDTO>> response = accountService.getAllAccounts();
        assertNotNull(response);
        verify(accountRepository, times(1)).findAccountByName(anyString());
    }

    @Test
    public void testGetAllAccountsByDepartment() {
        when(accountRepository.findAccountByDepartmentCode(anyString())).thenReturn(List.of());
        ResponseEntity<List<AccountResponseDTO>> response = accountService.getAllAccounts();
        assertNotNull(response);
        verify(accountRepository, times(1)).findAccountByDepartmentCode(anyString());
    }

}

