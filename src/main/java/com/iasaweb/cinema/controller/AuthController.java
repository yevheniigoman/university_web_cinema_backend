package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.service.AuthService;
import com.iasaweb.cinema.dto.LoginRequestBody;
import com.iasaweb.cinema.dto.LoginResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBody> login(@RequestBody LoginRequestBody loginRequest) {
        LoginResponseBody body = authService.login(loginRequest);
        return ResponseEntity.ok(body);
    }
}
