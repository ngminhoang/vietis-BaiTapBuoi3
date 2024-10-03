package com.example.vietisbaitapbuoi3.utils;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
