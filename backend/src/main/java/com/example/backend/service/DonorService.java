package com.example.backend.service;

import com.example.backend.dto.FoodPostRequest;
import com.example.backend.model.FoodPost;
import com.example.backend.model.FoodStatus;
import com.example.backend.model.Users;
import com.example.backend.repository.FoodPostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonorService {

    private final FoodPostRepo foodPostRepo;

    public FoodPost createFood(FoodPostRequest request, Users donor) {

        FoodPost post = FoodPost.builder()
                .title(request.getTitle())
                .quantity(request.getQuantity())
                .pickupTime(request.getPickupTime())
                .location(request.getLocation())
                .status(FoodStatus.PENDING)
                .donor(donor)
                .build();

        return foodPostRepo.save(post);
    }

    public List<FoodPost> getMyFoods(Users donor) {
        return foodPostRepo.findByDonor(donor);
    }

}
