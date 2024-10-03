package com.example.vietisbaitapbuoi3.repositories;

import com.example.vietisbaitapbuoi3.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.mail = :mail")
    Optional<Account> findByMail(String mail);

    List<Account> findAccountByName(String name);
    List<Account> findAccountByDepartmentCode(String departmentCode);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.department = null WHERE a.department.id = :departmentId")
    void clearDepartmentFromAccounts(@Param("departmentId") Long departmentId);
}
