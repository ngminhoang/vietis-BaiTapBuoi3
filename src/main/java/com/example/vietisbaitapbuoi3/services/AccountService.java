package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {

    // Lấy tất cả tài khoản
    ResponseEntity<List<AccountResponseDTO>> getAllAccounts();

    // Lấy danh sách tài khoản theo tên
    ResponseEntity<List<AccountResponseDTO>> getAllAccountsByName(String name);

    // Lấy danh sách tài khoản theo phòng ban
    ResponseEntity<List<AccountResponseDTO>> getAllAccountsByDepartment(String code);

    // Lấy thông tin tài khoản theo ID
    ResponseEntity<AccountResponseDTO> getAccountsById(Long id);

    // Tạo mới tài khoản
    ResponseEntity<AccountResponseDTO> createAccount(Account account);

    // Xóa tài khoản theo ID
    ResponseEntity<AccountResponseDTO> delete(Long id);

    // Cập nhật thông tin tài khoản
    ResponseEntity<AccountResponseDTO> update(Account account);

    // Lấy danh sách tài khoản có điểm số cao nhất
    ResponseEntity<List<AccountScoreCountInforDTO>> getTopAccount();

    // Đổi mật khẩu tài khoản
    ResponseEntity<AccountResponseDTO> changePassword(Account account, ChangePasswordDTO changePasswordDTO);

    // Tải lên ảnh đại diện cho tài khoản
    ResponseEntity<AccountResponseDTO> uploadImg(Account account, MultipartFile file);
}
