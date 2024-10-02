package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    ResponseEntity<List<Department>>getAllDepartment();
    ResponseEntity<List<String>>getAllLevel();
    ResponseEntity<Department>createDepartment(Department department);
    ResponseEntity<Department>updateDepartment(Department department);
    ResponseEntity<Department>deleteDepartment(Long id);
}
