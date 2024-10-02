package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public ResponseEntity<List<Account>> getAccountsByDepartmentCode(@RequestParam String code) {
        return accountService.getAllAccountsByDepartment(code);
    }

    @Override
    public ResponseEntity<List<Account>> getAccountsByName(@RequestParam String name) {
        return accountService.getAllAccountsByName(name);
    }

    @Override
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAllAccountsById(id);
    }

    @Override
    public ResponseEntity<Account> delete(@PathVariable Long id) {
        return accountService.delete(id);
    }

    @Override
    public ResponseEntity<Account> update(@RequestBody Account account) {
        return accountService.update(account);
    }

    @Override
    public ResponseEntity<Account> create(@RequestBody Account account){
        return accountService.createAccount(account);
    }
}
