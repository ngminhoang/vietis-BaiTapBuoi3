package com.example.vietisbaitapbuoi3.services;


import com.example.vietisbaitapbuoi3.authentication.AuthenticationRequest;
import com.example.vietisbaitapbuoi3.authentication.AuthenticationResponse;
import com.example.vietisbaitapbuoi3.authentication.Register;
import com.example.vietisbaitapbuoi3.entities.Account;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse register(Register request);

    ResponseEntity<Boolean> authenEmployee(Account account);

    ResponseEntity<Boolean> authenAdmin(Account account);
}
