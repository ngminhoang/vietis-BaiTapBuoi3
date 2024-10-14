package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.RegisterDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/public")
public interface AuthController {
    @PostMapping("/login")  // API dang nhap
    ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody AuthenticationRequestDTO request
    );

    @PostMapping("/register")   // API dang ky
    ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterDTO request
    );

    @PostMapping("/authen_admin")   // API kiem tra quyen admin
    ResponseEntity<Boolean> authenAdmin(
            @AuthenticationPrincipal Account user
    );

    @PostMapping("/authen_employee")    //API kiem tra quyen employee
    ResponseEntity<Boolean> authenEmployee(
            @AuthenticationPrincipal Account user
    );
}
