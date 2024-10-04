package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountDTO;
import com.example.vietisbaitapbuoi3.entities.dto.AccountScoreCountInforDTO;
import com.example.vietisbaitapbuoi3.services.AccountService;
import com.example.vietisbaitapbuoi3.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PublicControllerImpl implements PublicController {

    @Autowired
    AccountService accountService;
    @Autowired
    DepartmentService departmentService;

    @Override
    public ResponseEntity<List<Account>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Override
    public ResponseEntity<List<Department>> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @Override
    public ResponseEntity<List<String>> getAllLevel() {
        return departmentService.getAllLevel();
    }

    @Override
    public ResponseEntity<List<AccountScoreCountInforDTO>> getTopAccount() {
        return accountService.getTopAccount();
    }

    @GetMapping("/redirectByRole")
    public String redirectByRole(@AuthenticationPrincipal Account user) {
        if (user == null) {
            return "/login";
        }
        switch (user.getRole()) {
            case ROLE_ADMIN:
                return "/admin/dashboard";
            case ROLE_EMPLOYEE:
                return "/employee/dashboard";
            case ROLE_MANAGER:
                return "/manager/dashboard";
            default:
                return "/";
        }
    }
}
