package com.example.jwt.custom;

import com.example.jwt.entity.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = User.class)
public interface UserCustom {
Integer getId();
String getUsername();
String getPassword();
}
