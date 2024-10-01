package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
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
    public ResponseEntity<List<Score>> getAllScoreByAccount(@AuthenticationPrincipal Account user) {
        return scoreService.getAllScoreByAccount(user);
    }
}
