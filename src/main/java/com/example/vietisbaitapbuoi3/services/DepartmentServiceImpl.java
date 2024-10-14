package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.DAO.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment() {
        try {
            return ResponseEntity.ok(departmentRepository.findAll().stream().map(DepartmentResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<String>> getAllLevel() {
        try {
            return ResponseEntity.ok(Level.AllValue());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<DepartmentResponseDTO> createDepartment(Department department) {
        try {
            Department savedDepartment = departmentRepository.save(department);
            savedDepartment.setCode("DEP-" + savedDepartment.getId().toString());
            return ResponseEntity.ok(new DepartmentResponseDTO(departmentRepository.save(savedDepartment)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(Department department) {
        try {
            Optional<Department> originDepartmentOpt = departmentRepository.findById(department.getId());

            if (originDepartmentOpt.isPresent()) {
                Department originDepartment = originDepartmentOpt.get();
                Field[] fields = Department.class.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object newValue = field.get(department);
                        Object originalValue = field.get(originDepartment);

                        if (newValue != null && !newValue.equals(originalValue)) {
                            field.set(originDepartment, newValue);
                        }
                    } catch (IllegalAccessException e) {
                        System.err.println("Error accessing field: " + field.getName() + " - " + e.getMessage());
                    }
                }

                return ResponseEntity.ok(new DepartmentResponseDTO(departmentRepository.save(originDepartment)));
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<DepartmentResponseDTO> deleteDepartment(Long id) {
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(id);

            if (departmentOptional.isPresent()) {
                accountRepository.clearDepartmentFromAccounts(id);
                departmentRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

