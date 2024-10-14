package com.example.vietisbaitapbuoi3.DAO.repositories;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    // Lấy danh sách điểm của tài khoản theo đối tượng Account
    List<Score> getScoreByAccount(Account account);

    // Tìm kiếm điểm theo ID tài khoản, tháng và năm
    @Query("SELECT s FROM Score s WHERE s.account.id = :accountId AND FUNCTION('MONTH', s.date) = :month AND FUNCTION('YEAR', s.date) = :year")
    List<Score> findScoresByAccountIdAndMonthAndYear(@Param("accountId") Long accountId,
                                                     @Param("month") int month,
                                                     @Param("year") int year);

    // Tìm kiếm điểm theo ID phòng ban, tháng và năm
    @Query("SELECT s FROM Score s WHERE s.account.department.id = :departmentId AND FUNCTION('MONTH', s.date) = :month AND FUNCTION('YEAR', s.date) = :year")
    List<Score> findScoresByDepartmentIdAndMonthAndYear(@Param("departmentId") Long departmentId,
                                                        @Param("month") int month,
                                                        @Param("year") int year);
}

