package com.example.backend.repository;

import com.example.backend.model.FoodPost;
import com.example.backend.model.FoodStatus;
import com.example.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodPostRepo extends JpaRepository<FoodPost,Long> {
    List<FoodPost> findByDonor(Users donor);
    List<FoodPost> findByStatus(FoodStatus status);
    List<FoodPost> findByAssignedDriver(Users driver);
    Optional<FoodPost> findFirstByAssignedDriver(Users driver);
    Optional<FoodPost> findFirstByAssignedDriverAndStatus(Users driver, FoodStatus status);
    List<FoodPost> findByStatusNot(FoodStatus status);
    List<FoodPost> findByAssignedDriverAndStatusNot(Users driver, FoodStatus status);

}
