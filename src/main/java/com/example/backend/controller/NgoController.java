package com.example.backend.controller;

import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.NgoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ngo")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class NgoController {

    private final NgoService ngoService;

    @GetMapping("/available-food")
    public List<FoodPost> getAvailableFood() {
        return ngoService.getAvailableFood();
    }

    @PatchMapping("/claim/{id}")
    public FoodPost claimFood(@PathVariable Long id, HttpServletRequest http)  {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Users ngo = userDetails.getUser();

        return ngoService.claimFood(id, ngo);
    }
}
