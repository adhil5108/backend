package com.example.backend.controller;

import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import com.example.backend.service.NgoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ngo")
@RequiredArgsConstructor
public class NgoController {

    private final NgoService ngoService;

    @GetMapping("/available-food")
    public List<FoodPost> getAvailableFood() {
        return ngoService.getAvailableFood();
    }

    @PatchMapping("/claim/{id}")
    public FoodPost claimFood(@PathVariable Long id, HttpServletRequest http) throws Exception {

        Users ngo = (Users) http.getAttribute("user");

        return ngoService.claimFood(id, ngo);
    }
}
