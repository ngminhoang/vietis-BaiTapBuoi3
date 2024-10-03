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

import java.sql.Date;
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
        return ResponseEntity.ok(scoreRepository.getScoreByAccount(account));
    }

    @Override
    public ResponseEntity<Score> createScore(Score score) {
        score.setDate(new Date(System.currentTimeMillis()).toLocalDate());
        return ResponseEntity.ok(scoreRepository.save(score));
    }

    @Override
    public ResponseEntity<List<Score>> getAllScoreByAccountId(Long id, Integer month, Integer year) {
        return ResponseEntity.ok(scoreRepository.findScoresByAccountIdAndMonthAndYear(id,month,year));
    }

    @Override
    public ResponseEntity<List<Score>> getAllScoreByDepartmentId(Long id, Integer month, Integer year) {
        return ResponseEntity.ok(scoreRepository.findScoresByDepartmentIdAndMonthAndYear(id,month,year));
    }

    @Override
    public ResponseEntity<List<DepartmentScoreCountDTO>> getAnalyzeDepartmentScore() {
        return ResponseEntity.ok(departmentRepository.getDepartmentWithScoreCount());
    }

    @Override
    public ResponseEntity<List<AccountScoreCountDTO>> getAnalyzeEmployeeScore() {
        return ResponseEntity.ok(accountRepository.getAccountWithScoreCount());
    }
}
