package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScoreService {
    ResponseEntity<List<Score>> getAllScoreByAccount(Account account);
}
