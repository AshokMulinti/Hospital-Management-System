package com.ashok.Hospital_Management.controller;

import com.ashok.Hospital_Management.dto.UserRequest;
import com.ashok.Hospital_Management.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest request) {
        String token = service.login(request);
        return ResponseEntity.ok("token: "+token);
    }

}
