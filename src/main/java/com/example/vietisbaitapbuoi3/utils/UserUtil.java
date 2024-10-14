package com.example.vietisbaitapbuoi3.utils;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.DAO.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {

    private static DepartmentRepository departmentRepository;
    private static AccountRepository accountRepository;
    @Autowired
    public UserUtil(DepartmentRepository departmentRepository, AccountRepository accountRepository) {
        UserUtil.departmentRepository = departmentRepository;
        UserUtil.accountRepository = accountRepository;
    }


    public static Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }
    public static Account getAccountById(Long id) {
        return  accountRepository.findById(id).get();
    }
}
