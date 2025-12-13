package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FoodPostRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50, message = "Title must be between 3–50 characters")
    private String title;

    @NotBlank(message = "Quantity cannot be empty")
    private String quantity;

    @NotBlank(message = "Pickup time is required")
    private String pickupTime;

    @NotBlank(message = "Location cannot be empty")
    private String location;
}
