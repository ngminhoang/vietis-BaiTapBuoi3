package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.repositories.DepartmentRepository;
import com.example.vietisbaitapbuoi3.repositories.ScoreRepository;
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
    public ResponseEntity<List<Score>> getAllScoreByAccount(Account account) {
        try {
            List<Score> scores = scoreRepository.getScoreByAccount(account);
            if (scores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<Score> createScore(Score score) {
        try {
            score.setDate(LocalDate.now());
            Score savedScore = scoreRepository.save(score);
            if (savedScore == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(savedScore);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<Score>> getAllScoreByAccountId(Long id, Integer month, Integer year) {
        try {
            List<Score> scores = scoreRepository.findScoresByAccountIdAndMonthAndYear(id, month, year);
            if (scores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<Score>> getAllScoreByDepartmentId(Long id, Integer month, Integer year) {
        try {
            List<Score> scores = scoreRepository.findScoresByDepartmentIdAndMonthAndYear(id, month, year);
            if (scores.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(scores);
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

