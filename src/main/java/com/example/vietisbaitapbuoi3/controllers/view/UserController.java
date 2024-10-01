package com.example.vietisbaitapbuoi3.controllers.view;

import com.example.vietisbaitapbuoi3.entities.Account;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Change to RestController to return plain text
public class UserController {

    @GetMapping("/redirectByRole")
    public String redirectByRole(@AuthenticationPrincipal Account user) {
        if (user == null) {
            return "/login"; // Return the login URL as text
        }

        // Return redirect URL based on the user's role
        switch (user.getRole()) {
            case ADMIN:
                return "/admin/dashboard";
            case EMPLOYEE:
                return "/employee/dashboard";
            case MANAGER:
                return "/manager/dashboard";
            default:
                return "/";
        }
    }
}
