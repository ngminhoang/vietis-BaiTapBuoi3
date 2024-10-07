package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.authentication.AuthenticationResponse;
import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.entities.dto.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok( accountRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Account>> getAllAccountsByName(String name) {
        return ResponseEntity.ok( accountRepository.findAccountByName(name));
    }

    @Override
    public ResponseEntity<List<Account>> getAllAccountsByDepartment(String code) {
        return ResponseEntity.ok( accountRepository.findAccountByDepartmentCode(code));
    }

    @Override
    public ResponseEntity<Account> getAccountsById(Long id) {
        Optional<Account> result = accountRepository.findById(id);
        if(result!=null) {
            return ResponseEntity.ok( result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        account.setRole(Role.ROLE_EMPLOYEE);
        account.setPassword(passwordEncoder.encode("123456"));
        Account newAccount =  accountRepository.save(account);
        newAccount.setCode("STAFF-"+newAccount.getId().toString());
        return ResponseEntity.ok(accountRepository.save(newAccount));
    }

    @Override
    public ResponseEntity<Account> delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        try {
            if(account.isPresent()) {
                accountRepository.delete(account.get());
                return ResponseEntity.ok(account.get());
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return null;
    }

    @Override
    public ResponseEntity<Account> update(Account account) {
        Optional<Account> originAccountOpt = accountRepository.findById(account.getId());
        if (originAccountOpt.isPresent()) {
            Account originAccount = originAccountOpt.get();

            Field[] fields = Account.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object newValue = field.get(account);
                    Object originalValue = field.get(originAccount);

                    if (newValue != null && !newValue.equals(originalValue)) {
                        field.set(originAccount, newValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return ResponseEntity.ok(accountRepository.save(originAccount));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<AccountScoreCountInforDTO>> getTopAccount() {
        return ResponseEntity.ok(accountRepository.getTop10AccountsByScoreDifference(PageRequest.of(0, 10) ));
    }

    @Override
    public ResponseEntity<Account> changePassword(Account user, ChangePasswordDTO changePasswordDTO) {
        String encodeOldPassword = passwordEncoder.encode(changePasswordDTO.getOldPassword());

        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            String encodeNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
            user.setPassword(encodeNewPassword);
            return ResponseEntity.ok(accountRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }
}
