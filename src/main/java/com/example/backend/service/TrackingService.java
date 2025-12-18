package com.example.backend.service;

import com.example.backend.dto.TrackingResponse;
import com.example.backend.model.FoodPost;
import com.example.backend.repository.FoodPostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final FoodPostRepo foodPostRepo;

    public TrackingResponse getTrackingInfo(Long foodId) {

        FoodPost post = foodPostRepo.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        return new TrackingResponse(
                post.getLocationurl(),
                post.getStatus().name()
        );
    }
}
