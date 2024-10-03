package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public interface DepartmentController {
    @GetMapping("/departments")
    ResponseEntity<List<Department>> getAllDepartments();
    @PostMapping("/departments")
    ResponseEntity<Department> create(@RequestBody Department department);
    @PutMapping("/departments")
    ResponseEntity<Department> update(@RequestBody Department department);
    @DeleteMapping("/departments/{id}")
    ResponseEntity<Department> delete(@PathVariable Long id);
}
