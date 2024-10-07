package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Score;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.DepartmentScoreCountDTO;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.repositories.DepartmentRepository;
import com.example.vietisbaitapbuoi3.repositories.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ScoreServiceTest {

    @InjectMocks
    private ScoreServiceImpl scoreService;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    private Score score;
    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account();
        account.setId(1L);
        score = new Score();
        score.setId(1L);
        score.setAccount(account);
    }

    @Test
    public void testGetAllScoreByAccount_Success() {
        when(scoreRepository.getScoreByAccount(any(Account.class))).thenReturn(List.of(score));

        ResponseEntity<List<Score>> response = scoreService.getAllScoreByAccount(account);

        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        verify(scoreRepository, times(1)).getScoreByAccount(any(Account.class));
    }

    @Test
    public void testCreateScore_Success() {
        when(scoreRepository.save(any(Score.class))).thenReturn(score);

        ResponseEntity<Score> response = scoreService.createScore(score);

        assertNotNull(response);
        assertEquals(score, response.getBody());
        verify(scoreRepository, times(1)).save(any(Score.class));
    }

    @Test
    public void testGetAllScoreByAccountId_Success() {
        when(scoreRepository.findScoresByAccountIdAndMonthAndYear(anyLong(), anyInt(), anyInt())).thenReturn(List.of(score));

        ResponseEntity<List<Score>> response = scoreService.getAllScoreByAccountId(1L, 10, 2024);

        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        verify(scoreRepository, times(1)).findScoresByAccountIdAndMonthAndYear(anyLong(), anyInt(), anyInt());
    }

    @Test
    public void testGetAllScoreByDepartmentId_Success() {
        when(scoreRepository.findScoresByDepartmentIdAndMonthAndYear(anyLong(), anyInt(), anyInt())).thenReturn(List.of(score));

        ResponseEntity<List<Score>> response = scoreService.getAllScoreByDepartmentId(1L, 10, 2024);

        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        verify(scoreRepository, times(1)).findScoresByDepartmentIdAndMonthAndYear(anyLong(), anyInt(), anyInt());
    }

    @Test
    public void testGetAnalyzeDepartmentScore_Success() {
        DepartmentScoreCountDTO departmentScore = new DepartmentScoreCountDTO(1L, "IT", 5L,5L);
        when(departmentRepository.getDepartmentWithScoreCount()).thenReturn(List.of(departmentScore));

        ResponseEntity<List<DepartmentScoreCountDTO>> response = scoreService.getAnalyzeDepartmentScore();

        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        assertEquals(departmentScore, response.getBody().get(0));
        verify(departmentRepository, times(1)).getDepartmentWithScoreCount();
    }

    @Test
    public void testGetAnalyzeEmployeeScore_Success() {
        AccountScoreCountDTO accountScore = new AccountScoreCountDTO(1L, "John", 10L, 10L);
        when(accountRepository.getAccountWithScoreCount()).thenReturn(List.of(accountScore));

        ResponseEntity<List<AccountScoreCountDTO>> response = scoreService.getAnalyzeEmployeeScore();

        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        assertEquals(accountScore, response.getBody().get(0));
        verify(accountRepository, times(1)).getAccountWithScoreCount();
    }
}
