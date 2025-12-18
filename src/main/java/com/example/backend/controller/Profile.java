package com.example.backend.controller;

import com.example.backend.model.Users;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.Jwtservice;
import com.example.backend.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class Profile {
    private final ProfileService profileService;

    @GetMapping()
    public Users get() {
        return profileService.getCurrentUser();
    }
}
