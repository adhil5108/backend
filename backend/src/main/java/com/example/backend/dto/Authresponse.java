package com.example.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authresponse{
    private String token;
    private String email;
    private String role;
    private String message;
}
