package com.example.backend.controller;

import com.example.backend.dto.TrackingResponse;
import com.example.backend.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/track")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    @GetMapping("/{foodId}")
    public TrackingResponse getLocation(@PathVariable Long foodId) {
        return trackingService.getTrackingInfo(foodId);
    }
}
