package com.example.backend.controller;

import com.example.backend.dto.FoodPostRequest;
import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import com.example.backend.service.DonorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/donor")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DonorController {

    private final DonorService donorService;

    @PostMapping(value = "/food", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FoodPost createFood(
            @Valid @ModelAttribute FoodPostRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image,
            HttpServletRequest http
    ) throws Exception {

        Users donor = (Users) http.getAttribute("user");
        return donorService.createFood(request, donor, image);
    }


    @GetMapping("/my-foods")
    public List<FoodPost> getMyFoods(HttpServletRequest http) {

        Users donor = (Users) http.getAttribute("user");

        return donorService.getMyFoods(donor);
    }
}
