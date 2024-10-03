package com.example.vietisbaitapbuoi3.controllers.view;

import com.example.vietisbaitapbuoi3.entities.Account;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller // Change to RestController to return plain text
public class UserController {

    @GetMapping("/user/department_score")
    public String departmentScore() {
        return "user/department_score";
    }

    @GetMapping("/user/employee_score")
    public String employeeScore() {
        return "user/employee_score";
    }
}
