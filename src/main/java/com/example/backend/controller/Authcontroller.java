package com.example.backend.controller;

import com.example.backend.dto.Authresponse;
import com.example.backend.dto.Loginrequest;
import com.example.backend.dto.Signuprequest;
import com.example.backend.service.Authservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Handles user signup and login")
public class Authcontroller {

    private final Authservice authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/signup")
    public Authresponse signup(@Valid @RequestBody Signuprequest request) {
        return authService.signup(request);
    }


    @Operation(summary = "Login user")
    @PostMapping("/login")
    public Authresponse login(@Valid @RequestBody Loginrequest request){
        return authService.login(request);
    }
}
