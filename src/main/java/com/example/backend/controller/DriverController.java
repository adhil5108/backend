package com.example.backend.controller;

import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.DriverService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "driver api", description = "apis for driver ")
public class DriverController {

    private final DriverService driverService;


    @GetMapping("/my-deliveries")
    public List<FoodPost> getMyDeliveries() {
        return driverService.getMyDeliveries(driverService.getDriver());
    }


    @PostMapping("/update-location")
    public FoodPost updateLocation(@RequestParam String livelocation)  {
        return driverService.updateLocation(driverService.getDriver(), livelocation);
    }


    @PostMapping("/checklist")
    public FoodPost updateChecklist()  {
        return driverService.updateChecklist(driverService.getDriver());
    }
}
