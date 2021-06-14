package com.example.jwt.controller;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.entity.Auditing;
import com.example.jwt.entity.User;
import com.example.jwt.jwtwriter.JwtProwider;
import com.example.jwt.repository.AuditingRepository;
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

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LogInController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProwider jwtProwider;
    @Autowired
    AuditingRepository triggerRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()));
            String token = jwtProwider.generateToken(loginDto.getUsername());
            User user = userRepository.findByUsername(loginDto.getUsername());
           boolean b = triggerRepository.existsByUser_id(user.getId());
            if (b){
                Optional<Auditing> trigger=triggerRepository.getByUser_id(user.getId());
                if (trigger.isPresent()){
                Auditing auditing=trigger.get();
                auditing.setToken(token);
                triggerRepository.save(auditing);
                }
            }else {
                Auditing trigger=new Auditing(user,token);
            triggerRepository.save(trigger);
            }
            return ResponseEntity.status(201).body(new ApiResponse(token, true));
        } catch (BadCredentialsException b) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("username yoki password xato", false));
        }
    }
}
