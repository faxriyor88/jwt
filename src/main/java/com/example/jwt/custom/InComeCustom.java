package com.example.jwt.custom;

import com.example.jwt.entity.InCome;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(types = InCome.class)
public interface InComeCustom {
Integer getId();
String getFrom_card_id();
String getTo_card_id();
Double getAmount();
LocalDate getDate();

}

