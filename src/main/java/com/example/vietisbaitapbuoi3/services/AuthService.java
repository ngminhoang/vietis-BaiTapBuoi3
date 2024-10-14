package com.example.vietisbaitapbuoi3.services;


import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AuthenticationResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.RegisterDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    // Đăng nhập hệ thống
    AuthenticationResponseDTO login(AuthenticationRequestDTO request);

    // Đăng ký tài khoản mới
    AuthenticationResponseDTO register(RegisterDTO request);

    // Xác thực nhân viên
    ResponseEntity<Boolean> authenEmployee(Account account);

    // Xác thực quản trị viên
    ResponseEntity<Boolean> authenAdmin(Account account);
}
