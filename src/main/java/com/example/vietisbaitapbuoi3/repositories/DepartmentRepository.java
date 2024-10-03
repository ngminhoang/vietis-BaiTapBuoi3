package com.example.vietisbaitapbuoi3.repositories;

import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.entities.dto.DepartmentScoreCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT new com.example.vietisbaitapbuoi3.entities.dto.DepartmentScoreCountDTO(d.id, d.name, " +
            "SUM(CASE WHEN s.type = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN s.type = false THEN 1 ELSE 0 END)) " +
            "FROM Department d LEFT JOIN d.accounts a LEFT JOIN a.scores s " +
            "GROUP BY d.id, d.name")
    List<DepartmentScoreCountDTO> getDepartmentWithScoreCount();
}
