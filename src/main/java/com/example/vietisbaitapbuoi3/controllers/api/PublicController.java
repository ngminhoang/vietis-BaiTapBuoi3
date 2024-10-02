package com.example.vietisbaitapbuoi3.controllers.api;

import com.example.vietisbaitapbuoi3.entities.Account;
import com.example.vietisbaitapbuoi3.entities.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public interface PublicController {
    @GetMapping("/get_all")
    ResponseEntity<List<Account>> getAllAccounts();
    @GetMapping("/department/get_all")
    ResponseEntity<List<Department>>getAllDepartment();
    @GetMapping("/level/get_all")
    ResponseEntity<List<String>>getAllLevel();
}
