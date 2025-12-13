package com.example.backend.controller;

import com.example.backend.dto.FoodPostRequest;
import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import com.example.backend.service.DonorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DonorController {

    private final DonorService donorService;

    @PostMapping("/food")
    public FoodPost createFood(@Valid @RequestBody FoodPostRequest request, HttpServletRequest http) {

        Users donor = (Users) http.getAttribute("user");

        return donorService.createFood(request, donor);
    }

    @GetMapping("/my-foods")
    public List<FoodPost> getMyFoods(HttpServletRequest http) {

        Users donor = (Users) http.getAttribute("user");

        return donorService.getMyFoods(donor);
    }
}
