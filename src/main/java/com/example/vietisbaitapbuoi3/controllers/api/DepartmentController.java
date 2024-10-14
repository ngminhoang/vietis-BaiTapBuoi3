package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public interface DepartmentController {
    @GetMapping("/departments") //API lay day sach phong ban
    ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments();
    @PostMapping("/departments")    //API tao phong ban
    ResponseEntity<DepartmentResponseDTO> create(@RequestBody Department department);
    @PutMapping("/departments") //API  cap nhat phong ban
    ResponseEntity<DepartmentResponseDTO> update(@RequestBody Department department);
    @DeleteMapping("/departments/{id}") //API xoa phong ban
    ResponseEntity<DepartmentResponseDTO> delete(@PathVariable Long id);
}
