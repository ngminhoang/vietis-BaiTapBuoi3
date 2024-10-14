package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    AccountService accountService;

    @Override

    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Override
    public ResponseEntity<AccountResponseDTO> get(Account user) {
        return ResponseEntity.ok(new AccountResponseDTO(user));
    }

    @Override
    @PostMapping("/employee/upload_img")
    public ResponseEntity<AccountResponseDTO> uploadImg(@AuthenticationPrincipal Account user,
                                             @RequestParam("file") MultipartFile file) {
            return accountService.uploadImg(user, file);
    }

    @Override
    public ResponseEntity<AccountResponseDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, @AuthenticationPrincipal Account user) {
        return accountService.changePassword(user, changePasswordDTO);
    }

    @Override
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByDepartmentCode(@RequestParam String code) {
        return accountService.getAllAccountsByDepartment(code);
    }

    @Override
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByName(@RequestParam String name) {
        return accountService.getAllAccountsByName(name);
    }

    @Override
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        return accountService.getAccountsById(id);
    }

    @Override
    public ResponseEntity<AccountResponseDTO> delete(@PathVariable Long id) {
        return accountService.delete(id);
    }


    @Override
    public ResponseEntity<AccountResponseDTO> update(@RequestBody AccountRequestDTO account) {
        return accountService.update(new Account(account));
    }

    @Override
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO account) {
        return accountService.createAccount(new Account(account));
    }
}
