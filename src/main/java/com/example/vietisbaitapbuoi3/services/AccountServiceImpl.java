package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok( accountRepository.findAll());
    }

    @Override
    public ResponseEntity<Account> createAccount(Account ingredients) {
        return ResponseEntity.ok( accountRepository.save(ingredients));
    }
}
