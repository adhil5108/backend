package com.example.backend.service;

import com.example.backend.model.FoodPost;
import com.example.backend.model.FoodStatus;
import com.example.backend.model.Role;
import com.example.backend.model.Users;
import com.example.backend.repository.FoodPostRepo;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final FoodPostRepo foodPostRepo;
    private final UserRepository userRepo;
    private final NotificationService notificationService;

    public List<FoodPost> getAllFoods() {
        return foodPostRepo.findByStatusNot(FoodStatus.DELIVERED);
    }

    public List<Users> getAllDrivers() {
        return userRepo.findByRole(Role.valueOf("DRIVER"));
    }

    public FoodPost assignDriver(Long postId, Long driverId)  {

        FoodPost post = foodPostRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Food post not found"));

        Users driver = userRepo.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        if (!driver.getRole().name().equals("DRIVER")) {
            throw new RuntimeException("Selected user is not a driver");
        }

        post.setAssignedDriver(driver);
        post.setStatus(FoodStatus.DRIVER_ASSIGNED);

        FoodPost saved = foodPostRepo.save(post);

        notificationService.send(post.getDonor().getId(), "Driver assigned to your food post.");
        notificationService.send(post.getClaimedBy().getId(), "Driver assigned!");

        return saved;
    }
    public List<FoodPost> getDeliveredFoods() {
        return foodPostRepo.findByStatus(FoodStatus.DELIVERED);
    }

}
