package com.example.backend.service;

import com.example.backend.dto.Authresponse;
import com.example.backend.dto.Loginrequest;
import com.example.backend.dto.Signuprequest;
import com.example.backend.model.Users;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Authservice {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Jwtservice jwtservice;

    public Authresponse signup(Signuprequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("User already exists with this email");
        }

        Users user = Users.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .role(req.getRole())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtservice.generatetoken(user);

        return Authresponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("user registered succesfully")
                .build();
    }

    public Authresponse login(Loginrequest req) {

        Users user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("user not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtservice.generatetoken(user);


        return Authresponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("login succesful")
                .build();
    }
}
