package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScoreService {

    // Lấy tất cả điểm số theo tài khoản
    ResponseEntity<List<ScoreResponseDTO>> getAllScoreByAccount(Account account);

    // Tạo mới điểm số
    ResponseEntity<ScoreResponseDTO> createScore(Score score);

    // Lấy tất cả điểm số theo ID tài khoản, tháng và năm
    ResponseEntity<List<ScoreResponseDTO>> getAllScoreByAccountId(Long id, Integer month, Integer year);

    // Lấy tất cả điểm số theo ID phòng ban, tháng và năm
    ResponseEntity<List<ScoreResponseDTO>> getAllScoreByDepartmentId(Long id, Integer month, Integer year);

    // Phân tích và lấy thông tin tổng điểm của các phòng ban
    ResponseEntity<List<DepartmentScoreCountDTO>> getAnalyzeDepartmentScore();

    // Phân tích và lấy thông tin tổng điểm của nhân viên
    ResponseEntity<List<AccountScoreCountDTO>> getAnalyzeEmployeeScore();
}