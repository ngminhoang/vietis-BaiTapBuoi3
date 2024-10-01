package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.authentication.AuthenticationRequest;
import com.example.vietisbaitapbuoi3.authentication.AuthenticationResponse;
import com.example.vietisbaitapbuoi3.authentication.Register;
import com.example.vietisbaitapbuoi3.entities.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/public")
public interface AuthController {
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    );

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(
            @RequestBody Register request
    );

    @PostMapping("/authen_admin")
    ResponseEntity<Boolean> authenAdmin(
            @AuthenticationPrincipal Account user
    );

    @PostMapping("/authen_employee")
    ResponseEntity<Boolean> authenEmployee(
            @AuthenticationPrincipal Account user
    );
}
