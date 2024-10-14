package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreResponseDTO;
import com.example.vietisbaitapbuoi3.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreControllerImpl implements ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Override
    public ResponseEntity<List<ScoreResponseDTO>> getAllScoreByAccountId(Long id, Integer month, Integer year) {
        return scoreService.getAllScoreByAccountId(id, month, year);
    }

    @Override
    public ResponseEntity<List<ScoreResponseDTO>> getAllScoreByDepartmentId(Long id, Integer month, Integer year) {
        return scoreService.getAllScoreByDepartmentId(id, month, year);
    }

    @Override
    public ResponseEntity<ScoreResponseDTO> createScore(ScoreRequestDTO score) {
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
