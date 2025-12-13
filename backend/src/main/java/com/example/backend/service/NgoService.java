package com.example.backend.service;

import com.example.backend.model.FoodPost;
import com.example.backend.model.FoodStatus;
import com.example.backend.model.Users;
import com.example.backend.repository.FoodPostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NgoService {

    private final FoodPostRepo foodPostRepo;

    public List<FoodPost> getAvailableFood() {
        return foodPostRepo.findByStatus(FoodStatus.PENDING);
    }

    public FoodPost claimFood(Long id, Users ngo) throws Exception {
        FoodPost post = foodPostRepo.findById(id)
                .orElseThrow(() -> new Exception("Food post not found"));


        if (post.getStatus() != FoodStatus.PENDING) {
            throw new Exception("Food already claimed");
        }

        post.setStatus(FoodStatus.CLAIMED);
        post.setClaimedBy(ngo);

        return foodPostRepo.save(post);
    }
}

