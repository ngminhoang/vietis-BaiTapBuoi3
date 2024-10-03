package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScoreService {
    ResponseEntity<List<Score>> getAllScoreByAccount(Account account);
    ResponseEntity<Score> createScore(Score score);
    ResponseEntity<List<Score>> getAllScoreByAccountId(Long id, Integer month, Integer year);
    ResponseEntity<List<Score>> getAllScoreByDepartmentId(Long id, Integer month, Integer year);

}
