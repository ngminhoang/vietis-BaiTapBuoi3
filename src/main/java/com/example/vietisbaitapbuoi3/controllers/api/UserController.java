package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface UserController {

    @GetMapping("/admin/accounts")  //API lay toan do tai khoan
    ResponseEntity<List<AccountResponseDTO>> getAllAccounts();

    @GetMapping("/employee")    //API lay thong tin tai khoan
    ResponseEntity<AccountResponseDTO> get( @AuthenticationPrincipal Account user);

    @PostMapping("/employee/upload_img")    //API upload anh
    ResponseEntity<AccountResponseDTO> uploadImg(@AuthenticationPrincipal Account user, @RequestParam("file") MultipartFile file);

    @PostMapping("/employee/test")  //API thay doi mat khau
    ResponseEntity<AccountResponseDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, @AuthenticationPrincipal Account user);

    @GetMapping("/admin/accounts/by-department")    //API lau danh sach tai khoan thong qua phong ban
    ResponseEntity<List<AccountResponseDTO>> getAccountsByDepartmentCode(@RequestParam String name);

    @GetMapping("/admin/accounts/by-name")  //API lay danh sach thong tin tai khoan thong qua ten
    ResponseEntity<List<AccountResponseDTO>> getAccountsByName(@RequestParam String name);

    @GetMapping("/admin/accounts/{id}") //API lay tai khoan thong qua id
    ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id);

    @DeleteMapping("/admin/accounts/{id}")  //API xoa tai khoan thong qua id
    ResponseEntity<AccountResponseDTO> delete(@PathVariable Long id);

    @PutMapping("/admin/accounts")  //API cap nhat lai tai khoan
    ResponseEntity<AccountResponseDTO> update(@RequestBody AccountRequestDTO account);

    @PostMapping("/admin/accounts") //API tao moi tai khoan
    ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO account);
}
