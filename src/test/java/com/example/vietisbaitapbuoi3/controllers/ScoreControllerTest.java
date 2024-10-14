package com.example.vietisbaitapbuoi3.controllers;

import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.ScoreResponseDTO;
import com.example.vietisbaitapbuoi3.services.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoreService scoreService;

    private List<ScoreResponseDTO> scores;

    @BeforeEach
    public void setUp() throws Exception {
        scores = Arrays.asList(new ScoreResponseDTO(new Score()));
    }

    @Test
    public void testGetAllScoresByAccountId() throws Exception {
        Long accountId = 1L;
        Integer month = 10;
        Integer year = 2024;

        when(scoreService.getAllScoreByAccountId(accountId, month, year)).thenReturn(ResponseEntity.ok(scores));

        mvc.perform(get("/api/user/scores")
                        .param("id", String.valueOf(accountId))
                        .param("month", String.valueOf(month))
                        .param("year", String.valueOf(year)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllScoresByDepartmentId() throws Exception {
        Long departmentId = 2L;
        Integer month = 9;
        Integer year = 2023;

        when(scoreService.getAllScoreByDepartmentId(departmentId, month, year)).thenReturn(ResponseEntity.ok(scores));

        mvc.perform(get("/api/user/scores/list_by_department")
                        .param("id", String.valueOf(departmentId))
                        .param("month", String.valueOf(month))
                        .param("year", String.valueOf(year)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateScore() throws Exception {
        ScoreRequestDTO scoreRequest = new ScoreRequestDTO();
        Score score = new Score(scoreRequest);

        when(scoreService.createScore(any(Score.class))).thenReturn(ResponseEntity.ok(new ScoreResponseDTO(score)));

        mvc.perform(post("/api/user/scores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"someField\": \"someValue\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAnalyzeDepartmentScores() throws Exception {
        List<DepartmentScoreCountDTO> departmentScoreCounts = Arrays.asList(new DepartmentScoreCountDTO(1L, "IT", 1L, 2L), new DepartmentScoreCountDTO(2L, "ICT", 11L, 2L));

        when(scoreService.getAnalyzeDepartmentScore()).thenReturn(ResponseEntity.ok(departmentScoreCounts));

        mvc.perform(get("/api/user/scores/analyze/department"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAnalyzeEmployeeScores() throws Exception {
        List<AccountScoreCountDTO> accountScoreCounts = Arrays.asList(new AccountScoreCountDTO(1L, "hoang", 1L, 2L), new AccountScoreCountDTO(2L, "linh", 1L, 2L));

        when(scoreService.getAnalyzeEmployeeScore()).thenReturn(ResponseEntity.ok(accountScoreCounts));

        mvc.perform(get("/api/user/scores/analyze/employee"))
                .andExpect(status().isOk());
    }
}
