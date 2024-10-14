package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {

    // Lấy danh sách tất cả các phòng ban
    ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment();

    // Lấy danh sách tất cả các cấp bậc
    ResponseEntity<List<String>> getAllLevel();

    // Tạo mới một phòng ban
    ResponseEntity<DepartmentResponseDTO> createDepartment(Department department);

    // Cập nhật thông tin phòng ban
    ResponseEntity<DepartmentResponseDTO> updateDepartment(Department department);

    // Xóa một phòng ban theo ID
    ResponseEntity<DepartmentResponseDTO> deleteDepartment(Long id);
}
