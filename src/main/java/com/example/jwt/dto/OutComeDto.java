package com.example.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutComeDto {
    private Integer fromCard_id;
    private Integer toCard_id;
    private Double amount;
    private Double commision_amount;
    private LocalDate date;
}
