package com.example.jwt.controller;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.jwtwriter.JwtProwider;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LogInController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProwider jwtProwider;
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));
            String token = jwtProwider.generateToken(loginDto.getUsername());
            return ResponseEntity.status(201).body(new ApiResponse(token, true));
        } catch (BadCredentialsException b) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("username yoki password xato", false));
        }
    }
}
