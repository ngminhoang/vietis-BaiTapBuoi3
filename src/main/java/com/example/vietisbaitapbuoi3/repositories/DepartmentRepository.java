package com.example.vietisbaitapbuoi3.repositories;

import com.example.vietisbaitapbuoi3.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
