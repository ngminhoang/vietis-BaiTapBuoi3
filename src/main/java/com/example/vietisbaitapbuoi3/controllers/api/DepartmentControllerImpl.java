package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.repositories.DepartmentRepository;
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
    public ResponseEntity<List<Department>> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @Override
    public ResponseEntity<Department> create(Department department) {
        return departmentService.createDepartment(department);
    }

    @Override
    public ResponseEntity<Department> update(Department department) {
        return departmentService.updateDepartment(department);
    }
}
