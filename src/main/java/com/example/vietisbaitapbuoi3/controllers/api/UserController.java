package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public interface UserController {

    @GetMapping("/accounts")
    ResponseEntity<List<Account>> getAllAccounts();

    @GetMapping("/accounts/by-department")
    ResponseEntity<List<Account>> getAccountsByDepartmentCode(@RequestParam String name);

    @GetMapping("/accounts/by-name")
    ResponseEntity<List<Account>> getAccountsByName(@RequestParam String name);

    @GetMapping("/accounts/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable Long id);

    @DeleteMapping("/accounts/{id}")
    ResponseEntity<Account> delete(@PathVariable Long id);

    @PutMapping("/accounts")
    ResponseEntity<Account> update(@RequestBody Account account);

    @PostMapping("/accounts")
    ResponseEntity<Account> create(@RequestBody Account account);
}
