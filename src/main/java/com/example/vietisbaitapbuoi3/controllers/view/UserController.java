package com.example.vietisbaitapbuoi3.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/employee/department_score")
    public String departmentScore() {
        return "employee/department_score";
    }

    @GetMapping("/employee/employee_score")
    public String employeeScore() {
        return "employee/employee_score";
    }
}
