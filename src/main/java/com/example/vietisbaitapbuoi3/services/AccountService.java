package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.entities.dto.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.utils.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    ResponseEntity<Account> uploadImg(Account user,MultipartFile file);
}
