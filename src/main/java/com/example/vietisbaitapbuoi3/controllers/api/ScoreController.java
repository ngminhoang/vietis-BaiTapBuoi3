package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
import com.example.vietisbaitapbuoi3.entities.dto.ScoreRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
public interface ScoreController {

//    @GetMapping("/score/list")
//    ResponseEntity<List<Score>> getAllScoreByAccount(@AuthenticationPrincipal Account user);

    @GetMapping("/score/list")
    ResponseEntity<List<Score>> getAllScoreByAccountId(@RequestParam Long id, Integer month, Integer year);

    @GetMapping("/score/list_by_department")
    ResponseEntity<List<Score>> getAllScoreByDepartmentId(@RequestParam Long id, Integer month, Integer year);

    @PostMapping("/score")
    ResponseEntity<Score> createScore(@RequestBody ScoreRequestDTO score);
}
