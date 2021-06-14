package com.example.jwt.service;

import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyAuthService implements UserDetailsService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user: userRepository.findAll() ) {
            if (user.getUsername().equals(username)){
                return new org.springframework.security.core.userdetails.User(username, passwordEncoder.encode( user.getPassword()),new ArrayList<>());
            }
        }
        throw  new UsernameNotFoundException("Username xato");
    }
}
