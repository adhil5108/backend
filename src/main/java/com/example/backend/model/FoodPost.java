package com.example.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "food_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodPost {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String quantity;
    private String pickupTime;
    private String location;

  private String imageUrl;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Users donor;

    @ManyToOne
    @JoinColumn(name = "claimed_by_ngo")
    private Users claimedBy;

    @ManyToOne
    private Users assignedDriver;

    private String locationurl;

}
