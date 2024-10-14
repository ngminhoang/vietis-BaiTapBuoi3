package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
public interface ScoreController {

    @GetMapping("/score/list")  //API lay danh sach diem cua nhan vien qua id
    ResponseEntity<List<ScoreResponseDTO>> getAllScoreByAccountId(@RequestParam Long id, Integer month, Integer year);

    @GetMapping("/score/list_by_department")    //API lay danh sach diem cua phong ban
    ResponseEntity<List<ScoreResponseDTO>> getAllScoreByDepartmentId(@RequestParam Long id, Integer month, Integer year);

    @PostMapping("/score")  //API tao diem
    ResponseEntity<ScoreResponseDTO> createScore(@RequestBody ScoreRequestDTO score);

    @GetMapping("/score/analyze/department")    //API lay tong diem cua phong ban
    ResponseEntity<List<DepartmentScoreCountDTO>> getAnalyzeDepartmentScore();

    @GetMapping("/score/analyze/employee")  //API lay tong diem cua nhan vien
    ResponseEntity<List<AccountScoreCountDTO>> getAnalyzeEmployeeScore();

}
