package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<List<Account>> getAllAccounts();
    ResponseEntity<Account> createAccount(Account ingredients);
}
