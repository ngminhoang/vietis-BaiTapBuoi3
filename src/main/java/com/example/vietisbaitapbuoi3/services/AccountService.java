package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.entities.dto.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<List<Account>> getAllAccounts();
    ResponseEntity<List<Account>> getAllAccountsByName(String name);
    ResponseEntity<List<Account>> getAllAccountsByDepartment(String code);
    ResponseEntity<Account> getAccountsById(Long id);
    ResponseEntity<Account> createAccount(Account ingredients);
    ResponseEntity<Account> delete(Long id);
    ResponseEntity<Account> update(Account account);
    ResponseEntity<List<AccountScoreCountInforDTO>> getTopAccount();
    ResponseEntity<Account> changePassword(Account user, ChangePasswordDTO changePasswordDTO);
}
