package com.example.vietisbaitapbuoi3.DAO.repositories;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountInforDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Tìm tài khoản theo email
    @Query("SELECT a FROM Account a WHERE a.mail = :mail")
    Optional<Account> findByMail(String mail);

    // Tìm danh sách tài khoản theo tên
    List<Account> findAccountByName(String name);

    // Tìm danh sách tài khoản theo mã phòng ban
    List<Account> findAccountByDepartmentCode(String departmentCode);

    // Xóa phòng ban khỏi tài khoản theo ID phòng ban
    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.department = null WHERE a.department.id = :departmentId")
    void clearDepartmentFromAccounts(@Param("departmentId") Long departmentId);

    // Lấy danh sách tài khoản kèm số lượng điểm tích cực và tiêu cực
    @Query("SELECT new com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountDTO(a.id, a.name, " +
            "SUM(CASE WHEN s.type = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN s.type = false THEN 1 ELSE 0 END)) " +
            "FROM Account a LEFT JOIN a.scores s " +
            "GROUP BY a.id, a.name")
    List<AccountScoreCountDTO> getAccountWithScoreCount();

    // Lấy danh sách top 10 tài khoản có sự chênh lệch điểm tích cực và tiêu cực lớn nhất
    @Query("SELECT new com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountScoreCountInforDTO(a.id, a.name, a.imgPath, d.name) " +
            "FROM Account a LEFT JOIN a.scores s " +
            "LEFT JOIN a.department d " +
            "GROUP BY a.id, a.name, a.imgPath, d.name " +
            "ORDER BY (SUM(CASE WHEN s.type = true THEN 1 ELSE 0 END) - SUM(CASE WHEN s.type = false THEN 1 ELSE 0 END)) DESC")
    List<AccountScoreCountInforDTO> getTop10AccountsByScoreDifference(Pageable pageable);
}

