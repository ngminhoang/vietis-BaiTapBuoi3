package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


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
    public ResponseEntity<Account> getAllAccountsById(Long id) {
        return ResponseEntity.ok( accountRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        account.setRole(Role.EMPLOYEE);
        return ResponseEntity.ok( accountRepository.save(account));
    }

    @Override
    public ResponseEntity<Account> delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()) {
            accountRepository.delete(account.get());
            return ResponseEntity.ok(account.get());
        }
        return null;
    }

    @Override
    public ResponseEntity<Account> update(Account account) {
        Optional<Account> originAccountOpt = accountRepository.findById(account.getId());
        if (originAccountOpt.isPresent()) {
            Account originAccount = originAccountOpt.get();

            // Dùng Reflection để kiểm tra và cập nhật các trường thay đổi
            Field[] fields = Account.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Đảm bảo có thể truy cập các trường private
                try {
                    Object newValue = field.get(account);          // Lấy giá trị của trường từ đối tượng account
                    Object originalValue = field.get(originAccount); // Lấy giá trị của trường từ originAccount

                    // Nếu giá trị khác nhau thì cập nhật
                    if (newValue != null && !newValue.equals(originalValue)) {
                        field.set(originAccount, newValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Xử lý ngoại lệ truy cập
                }
            }

            // Sau khi cập nhật các trường, lưu lại đối tượng originAccount
            return ResponseEntity.ok(accountRepository.save(originAccount));
        }
        return ResponseEntity.notFound().build();
    }
}
