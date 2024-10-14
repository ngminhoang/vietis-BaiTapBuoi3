package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public interface PublicController {
    @GetMapping("/get_all") //API lay toan bo danh sach tai khoan nhan vien
    ResponseEntity<List<AccountResponseDTO>> getAllAccounts();
    @GetMapping("/department/get_all")  // API lay toan bo danh sach phong ban
    ResponseEntity<List<DepartmentResponseDTO>>getAllDepartment();
    @GetMapping("/level/get_all")   //API lay toan bo danh sach cac cap bac
    ResponseEntity<List<String>>getAllLevel();
    @GetMapping("/account/get_top") //API lay cac tai khoan co chi so diem cao
    ResponseEntity<List<AccountScoreCountInforDTO>>getTopAccount();
}
