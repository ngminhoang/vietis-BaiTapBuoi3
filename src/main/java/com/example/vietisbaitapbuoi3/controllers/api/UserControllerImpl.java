package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.dto.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.entities.dto.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.services.AccountService;
import com.example.vietisbaitapbuoi3.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    AccountService accountService;

    @Override

    public ResponseEntity<List<Account>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Override
    public ResponseEntity<Account> get(Account user) {
        return ResponseEntity.ok(user);
    }

    @Override
    @PostMapping("/employee/upload_img")
    public ResponseEntity<Account> uploadImg(@AuthenticationPrincipal Account user,
                                             @RequestParam("file") MultipartFile file) {
            return accountService.uploadImg(user, file);
    }

    @Override
    public ResponseEntity<Account> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, @AuthenticationPrincipal Account user) {
        return accountService.changePassword(user, changePasswordDTO);
    }

    @Override
    public ResponseEntity<List<Account>> getAccountsByDepartmentCode(@RequestParam String code) {
        return accountService.getAllAccountsByDepartment(code);
    }

    @Override
    public ResponseEntity<List<Account>> getAccountsByName(@RequestParam String name) {
        return accountService.getAllAccountsByName(name);
    }

    @Override
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountsById(id);
    }

    @Override
    public ResponseEntity<Account> delete(@PathVariable Long id) {
        return accountService.delete(id);
    }


    @Override
    public ResponseEntity<Account> update(@RequestBody AccountRequestDTO account) {
        return accountService.update(new Account(account));
    }

    @Override
    public ResponseEntity<Account> create(@RequestBody AccountRequestDTO account) {
        return accountService.createAccount(new Account(account));
    }
}
