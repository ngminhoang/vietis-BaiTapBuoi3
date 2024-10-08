package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.authentication.AuthenticationRequest;
import com.example.vietisbaitapbuoi3.authentication.AuthenticationResponse;
import com.example.vietisbaitapbuoi3.authentication.Register;
import com.example.vietisbaitapbuoi3.configuration.JwtService;
import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.enums.Role;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            Optional<Account> optionalUser = accountRepository.findByMail(request.getMail());
            if (optionalUser.isPresent()) {
                Account user = optionalUser.get();
                if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    String jwt = jwtService.generateToken(user);
                    return AuthenticationResponse.builder().token(jwt).build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AuthenticationResponse.builder().token(null).build();
    }

    @Override
    public AuthenticationResponse register(Register request) {
        try {
            if (accountRepository.findByMail(request.getMail()).isPresent()) {
                return null;
            }

            String encryptedPassword = passwordEncoder.encode(request.getPassword());

            Account user = Account.builder()
                    .gender(request.getGender())
                    .birthday(request.getBirthday())
                    .name(request.getName())
                    .mail(request.getMail())
                    .password(encryptedPassword)
                    .role(Role.ROLE_EMPLOYEE)
                    .build();

            Account savedAccount = accountRepository.save(user);
            savedAccount.setCode("STAFF-" + savedAccount.getId());
            accountRepository.save(savedAccount);

            String jwt = jwtService.generateToken(savedAccount);

            return AuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Boolean> authenAdmin(Account account) {
        return authenRole(account, Role.ROLE_ADMIN);
    }

    @Override
    public ResponseEntity<Boolean> authenEmployee(Account account) {
        return authenRole(account, Role.ROLE_EMPLOYEE);
    }

    private ResponseEntity<Boolean> authenRole(Account account, Role role) {
        try {
            Optional<Account> existingUser = accountRepository.findById(account.getId());
            boolean isAuthorized = existingUser.isPresent() && existingUser.get().getRole().equals(role);
            return ResponseEntity.ok(isAuthorized);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }
}
