package com.school.vaccinationportal.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<String> validateLogin(@RequestBody Map<String, String> creds) {
         if ("admin".equals(creds.get("username")) && "admin@123".equals(creds.get("password"))) {
            return ResponseEntity.ok("Login successful");
         }
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
