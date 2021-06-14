package com.example.jwt.repository;

import com.example.jwt.custom.CardCustom;
import com.example.jwt.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "card",excerptProjection = CardCustom.class)
public interface CardRepository extends JpaRepository<Card,Integer> {
 @Query(value = "select * from card where owner_id =(select user_id from auditing where token = ?1)",nativeQuery = true)
    Optional<Card> getCardOwner_id(String token);
}
