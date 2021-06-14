package com.example.jwt.custom;


import com.example.jwt.entity.Card;
import com.example.jwt.entity.OutCome;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(types = OutCome.class)
public interface OutComeCustom {
    Integer getId();
    Card getFrom_card_id();
    Card getTo_card_id();
    Double getAmount();
    LocalDate getDate();
    Double getCommision_amount();
}
