package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentResponseDTO;
import com.example.vietisbaitapbuoi3.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentControllerImpl implements DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Override
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @Override
    public ResponseEntity<DepartmentResponseDTO> create(Department department) {
        return departmentService.createDepartment(department);
    }

    @Override
    public ResponseEntity<DepartmentResponseDTO> update(Department department) {
        return departmentService.updateDepartment(department);
    }

    @Override
    public ResponseEntity<DepartmentResponseDTO> delete(Long id) {
        return departmentService.deleteDepartment(id);
    }
}
