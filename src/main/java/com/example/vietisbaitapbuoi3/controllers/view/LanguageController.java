package com.example.vietisbaitapbuoi3.controllers.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@Scope("session")
public class LanguageController {

    @PostMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request) {
        Locale locale = new Locale(lang);
        HttpSession session = request.getSession();
        session.setAttribute("currentLocale", locale);
        return "redirect:/"; // Redirect to the homepage or wherever necessary
    }
}

