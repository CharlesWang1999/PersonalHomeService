package com.charles.user.web.controller;

import com.charles.user.application.dto.*;
import com.charles.user.application.service.UserApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for authentication endpoints.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserApplicationService userApplicationService;

    public AuthController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return userApplicationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userApplicationService.login(request);
    }

    @PostMapping("/refresh")
    public LoginResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return userApplicationService.refreshToken(request);
    }
}
