package com.example.jwt.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InCome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Card from_card_id;
    @ManyToOne
    private Card to_card_id;
    private Double amount;
    private LocalDate date;

    public InCome(Card from_card_id, Card to_card_id, Double amount, LocalDate date) {
        this.from_card_id = from_card_id;
        this.to_card_id = to_card_id;
        this.amount = amount;
        this.date = date;
    }
}

