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

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account and returns a JWT token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Signup successful",
                            content = @Content(schema = @Schema(implementation = Authresponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User already exists / invalid input"
                    )
            }
    )
    @PostMapping("/signup")
    public Authresponse signup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User signup details",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Signuprequest.class))
            )
           @Valid @RequestBody Signuprequest request
    ) {
        return authService.signup(request);
    }


    @Operation(
            summary = "Login user",
            description = "Authenticates a user and returns a JWT token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login successful",
                            content = @Content(schema = @Schema(implementation = Authresponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid email or password"
                    )
            }
    )
    @PostMapping("/login")
    public Authresponse login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User login credentials",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Loginrequest.class))
            )
           @Valid @RequestBody Loginrequest request
    ) {
        return authService.login(request);
    }
}
