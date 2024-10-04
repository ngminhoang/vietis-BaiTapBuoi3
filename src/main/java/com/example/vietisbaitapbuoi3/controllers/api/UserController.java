package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.dto.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.entities.dto.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface UserController {

    @GetMapping("/admin/accounts")
    ResponseEntity<List<Account>> getAllAccounts();

    @GetMapping("/employee")
    ResponseEntity<Account> get( @AuthenticationPrincipal Account user);

    @PostMapping("/employee/upload_img")
    ResponseEntity<Account> uploadImg(@AuthenticationPrincipal Account user,
                                             @RequestParam("file") MultipartFile file);

    @PostMapping("/employee/change_password")
    ResponseEntity<Account> changePassword(@AuthenticationPrincipal Account user, @RequestBody ChangePasswordDTO changePasswordDTO);


    @GetMapping("/admin/accounts/by-department")
    ResponseEntity<List<Account>> getAccountsByDepartmentCode(@RequestParam String name);

    @GetMapping("/admin/accounts/by-name")
    ResponseEntity<List<Account>> getAccountsByName(@RequestParam String name);

    @GetMapping("/admin/accounts/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable Long id);

    @DeleteMapping("/admin/accounts/{id}")
    ResponseEntity<Account> delete(@PathVariable Long id);

    @PutMapping("/admin/accounts")
    ResponseEntity<Account> update(@RequestBody AccountRequestDTO account);

    @PostMapping("/admin/accounts")
    ResponseEntity<Account> create(@RequestBody AccountRequestDTO account);
}
