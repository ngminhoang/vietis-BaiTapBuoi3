package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.entities.enums.Level;
import com.example.vietisbaitapbuoi3.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<List<Department>> getAllDepartment() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @Override
    public ResponseEntity<List<String>> getAllLevel() {
        return ResponseEntity.ok(Level.AllValue());
    }

    @Override
    public ResponseEntity<Department> createDepartment(Department department) {
        return ResponseEntity.ok(departmentRepository.save(department));
    }

    @Override
    public ResponseEntity<Department> updateDepartment(Department department) {
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
                    e.printStackTrace();
                }
            }

            // Sau khi cập nhật các trường, lưu lại đối tượng originDepartment
            return ResponseEntity.ok(departmentRepository.save(originDepartment));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Department> deleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()) {
            departmentRepository.delete(department.get());
            return ResponseEntity.ok(department.get());
        }
        return null;
    }
}
