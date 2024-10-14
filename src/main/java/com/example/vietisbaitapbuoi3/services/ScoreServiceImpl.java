package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.DAO.repositories.DepartmentRepository;
import com.example.vietisbaitapbuoi3.DAO.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<List<ScoreResponseDTO>> getAllScoreByAccount(Account account) {
        try {
            List<Score> scores = scoreRepository.getScoreByAccount(account);
            if (scores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scores.stream().map(ScoreResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<ScoreResponseDTO> createScore(Score score) {
        try {
            score.setDate(LocalDate.now());
            Score savedScore = scoreRepository.save(score);
            if (savedScore == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(new ScoreResponseDTO(savedScore));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<ScoreResponseDTO>> getAllScoreByAccountId(Long id, Integer month, Integer year) {
        try {
            List<Score> scores = scoreRepository.findScoresByAccountIdAndMonthAndYear(id, month, year);
            if (scores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scores.stream().map(ScoreResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<ScoreResponseDTO>> getAllScoreByDepartmentId(Long id, Integer month, Integer year) {
        try {
            List<Score> scores = scoreRepository.findScoresByDepartmentIdAndMonthAndYear(id, month, year);
            if (scores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scores.stream().map(ScoreResponseDTO::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<DepartmentScoreCountDTO>> getAnalyzeDepartmentScore() {
        try {
            List<DepartmentScoreCountDTO> analysis = departmentRepository.getDepartmentWithScoreCount();
            if (analysis.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(analysis);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<AccountScoreCountDTO>> getAnalyzeEmployeeScore() {
        try {
            List<AccountScoreCountDTO> analysis = accountRepository.getAccountWithScoreCount();
            if (analysis.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(analysis);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

