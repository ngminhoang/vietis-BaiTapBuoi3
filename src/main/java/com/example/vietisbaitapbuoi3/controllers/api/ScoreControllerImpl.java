package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.ScoreRequestDTO;
import com.example.vietisbaitapbuoi3.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreControllerImpl implements ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Override
    public ResponseEntity<List<Score>> getAllScoreByAccountId(Long id, Integer month, Integer year) {
        return scoreService.getAllScoreByAccountId(id, month, year);
    }

    @Override
    public ResponseEntity<List<Score>> getAllScoreByDepartmentId(Long id, Integer month, Integer year) {
        return scoreService.getAllScoreByDepartmentId(id, month, year);
    }

    @Override
    public ResponseEntity<Score> createScore(ScoreRequestDTO score) {
        return scoreService.createScore(new Score(score));
    }

    @Override
    public ResponseEntity<List<DepartmentScoreCountDTO>> getAnalyzeDepartmentScore() {
        return scoreService.getAnalyzeDepartmentScore();
    }

    @Override
    public ResponseEntity<List<AccountScoreCountDTO>> getAnalyzeEmployeeScore() {
        return scoreService.getAnalyzeEmployeeScore();
    }

}
