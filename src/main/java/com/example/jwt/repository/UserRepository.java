package com.example.jwt.repository;

import com.example.jwt.custom.UserCustom;
import com.example.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user",excerptProjection = UserCustom.class)
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
