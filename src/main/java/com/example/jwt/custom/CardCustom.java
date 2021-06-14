package com.example.jwt.custom;

import com.example.jwt.entity.Card;
import com.example.jwt.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(types = Card.class)
public interface CardCustom {
    Integer getId();
    User getOwner();
    String getUsername();
    String getNumber();
    Double getBalance();
    LocalDate getExpire_date();
    boolean getActive();

}
