package com.example.jwt.service;

import com.example.jwt.dto.OutComeDto;
import com.example.jwt.entity.Card;
import com.example.jwt.entity.InCome;
import com.example.jwt.entity.OutCome;

import com.example.jwt.repository.CardRepository;
import com.example.jwt.repository.InComeRepository;
import com.example.jwt.repository.OutComeRepository;
import com.example.jwt.repository.AuditingRepository;
import com.example.jwt.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class OutComeInComeService {

    @Autowired
    OutComeRepository outComeRepository;
    @Autowired
    InComeRepository inComeRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    AuditingRepository ttttttRepository;

    public ApiResponse outComeWriter(OutComeDto outComeDto, HttpServletRequest httpServletRequest) {
            String token = httpServletRequest.getHeader("Authorization");
            token = token.substring(7);
            Optional<Card> cardOwner_id = cardRepository.getCardOwner_id(token);
            if (cardOwner_id.isPresent()) {
                Card card = cardOwner_id.get();
                if (card.getId() == outComeDto.getFromCard_id()) {
                    Optional<Card> optional1 = cardRepository.findById(outComeDto.getToCard_id());
                    if (optional1.isPresent()) {
                        double sum = outComeDto.getAmount();
                        double kom = sum * (outComeDto.getCommision_amount() / 100);
                        if (card.getBalance() >= sum + kom) {
                            OutCome outCome = new OutCome(card, optional1.get(),
                                    sum + kom, outComeDto.getDate(), outComeDto.getCommision_amount());
                            outComeRepository.save(outCome);
                            Card card1 = card;
                            card1.setBalance(card.getBalance() - (sum + kom));
                            cardRepository.save(card1);
                            InCome inCome = new InCome(card, optional1.get(), sum, outComeDto.getDate());
                            inComeRepository.save(inCome);
                            Card card2 = optional1.get();
                            card2.setBalance(card2.getBalance() + sum);
                            cardRepository.save(card1);
                            return new ApiResponse("O'tkazma muvaffaqiyatli ", true);
                        } else {
                            return new ApiResponse("Kartada pul yetarli emas !!!", false);
                        }
                    } else {
                        return new ApiResponse("Bunday qabul qiluvchi karta topilmadi !!!", false);
                    }

                } else {
                    return new ApiResponse("Karta sizga  tegishli emas !!!", false);
                }
            } else {
                return new ApiResponse("Sizda hali karta yo'q !!! ", false);
            }
    }

    public List<OutCome> getPersonOutCome(Integer owner_id){
        return outComeRepository.getPersonOutCome(owner_id);
    }
    public List<InCome> getPersonInCome(Integer owner_id){
        return inComeRepository.getPersonIncome(owner_id);
    }
}
