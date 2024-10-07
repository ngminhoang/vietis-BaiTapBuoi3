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


    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<Account> optionalUser = accountRepository.findByMail(request.getMail());
        if (optionalUser!=null) {
            Account user = optionalUser.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String jwt = jwtService.generateToken(user);
                return AuthenticationResponse.builder().token(jwt).build();
            }
        }
        return null;
    }

    public AuthenticationResponse register(Register request) {
        Optional<Account> existingUser = accountRepository.findByMail(request.getMail());
        if (existingUser.isPresent()) {
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

        Account account = accountRepository.save(user);
        account.setCode("STAFF-"+account.getId());
        accountRepository.save(account);
        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public ResponseEntity<Boolean> authenAdmin(Account account) {
        Optional<Account> existingUser = accountRepository.findById(account.getId());
        return existingUser.isPresent() && existingUser.get().getRole().equals(Role.ROLE_ADMIN)? ResponseEntity.ok(true): ResponseEntity.ok(false);
    }
    @Override
    public ResponseEntity<Boolean> authenEmployee(Account account) {
        Optional<Account> existingUser = accountRepository.findById(account.getId());
        return existingUser.isPresent() && existingUser.get().getRole().equals(Role.ROLE_EMPLOYEE)? ResponseEntity.ok(true): ResponseEntity.ok(false);
    }

}
