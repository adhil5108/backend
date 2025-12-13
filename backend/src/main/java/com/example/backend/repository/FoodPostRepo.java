package com.example.backend.repository;

import com.example.backend.model.FoodPost;
import com.example.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodPostRepo extends JpaRepository<FoodPost,Long> {
    List<FoodPost> findByDonor(Users donor);
}
