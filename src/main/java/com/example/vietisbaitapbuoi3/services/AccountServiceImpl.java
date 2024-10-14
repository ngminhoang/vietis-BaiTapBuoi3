package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ChangePasswordDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Role;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        try {
            return ResponseEntity.ok(accountRepository.findAll().stream().map(AccountResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<AccountResponseDTO>> getAllAccountsByName(String name) {
        try {
            return ResponseEntity.ok(accountRepository.findAccountByName(name).stream().map(AccountResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<AccountResponseDTO>> getAllAccountsByDepartment(String code) {
        try {
            return ResponseEntity.ok(accountRepository.findAccountByDepartmentCode(code).stream().map(AccountResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<AccountResponseDTO> getAccountsById(Long id) {
        try {
            Optional<Account> result = accountRepository.findById(id);
            if (result.isPresent()) {
                return ResponseEntity.ok(new AccountResponseDTO(result.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<AccountResponseDTO> createAccount(Account account) {
        try {
            account.setRole(Role.ROLE_EMPLOYEE);
            account.setPassword(passwordEncoder.encode("123456"));
            Account newAccount = accountRepository.save(account);
            newAccount.setCode("STAFF-" + newAccount.getId());
            return ResponseEntity.ok(new AccountResponseDTO(accountRepository.save(newAccount)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<AccountResponseDTO> delete(Long id) {
        try {
            Optional<Account> account = accountRepository.findById(id);
            if (account.isPresent()) {
                accountRepository.delete(account.get());
                return ResponseEntity.ok(new AccountResponseDTO(account.get()));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<AccountResponseDTO> update(Account account) {
        try {
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
                return ResponseEntity.ok(new AccountResponseDTO(accountRepository.save(originAccount)));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<AccountScoreCountInforDTO>> getTopAccount() {
        try {
            return ResponseEntity.ok(accountRepository.getTop10AccountsByScoreDifference(PageRequest.of(0, 10)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<AccountResponseDTO> changePassword(Account user, ChangePasswordDTO changePasswordDTO) {
        try {
            if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
                String encodeNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
                user.setPassword(encodeNewPassword);
                return ResponseEntity.ok(new AccountResponseDTO(accountRepository.save(user)));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<AccountResponseDTO> uploadImg(Account user, MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            String fileName = FileUtil.saveImageFile(user, file);
            user.setImgPath("/uploads/" + fileName);
            return ResponseEntity.ok(new AccountResponseDTO(accountRepository.save(user)));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
