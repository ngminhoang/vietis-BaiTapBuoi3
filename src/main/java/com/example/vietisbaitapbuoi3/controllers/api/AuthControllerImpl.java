package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.RegisterDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    private AuthService authService;

    public ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody AuthenticationRequestDTO request
    ) {
        AuthenticationResponseDTO res = authService.login(request);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterDTO request
    ) {
        AuthenticationResponseDTO res = authService.register(request);
        if (res == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<Boolean> authenAdmin(
            @AuthenticationPrincipal Account user
    ) {
        return authService.authenAdmin(user);

    }

    @Override
    public ResponseEntity<Boolean> authenEmployee(
            @AuthenticationPrincipal  Account user
    ) {
        return authService.authenEmployee(user);
    }
}
