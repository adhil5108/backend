package com.example.backend.controller;

import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import com.example.backend.model.Role;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "admin controller", description = "Handles admin apis")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "get all available foods")
    @GetMapping("/all-foods")
    public List<FoodPost> getAllFoods() {
        return adminService.getAllFoods();
    }

    @Operation(summary = "get all available drivers")
    @GetMapping("/drivers")
    public List<Users> getDrivers() {
        return adminService.getAllDrivers();
    }

    @Operation(summary = "assign a driver for a pickup")
    @PatchMapping("/assign-driver/{postId}")
    public FoodPost assignDriver(@PathVariable Long postId, @RequestParam Long driverId) {
        return adminService.assignDriver(postId, driverId);
    }

    @Operation(summary = "see delivered foods")
    @GetMapping("/delivered")
    public List<FoodPost> getDelivered() {
        return adminService.getDeliveredFoods();
    }

}
