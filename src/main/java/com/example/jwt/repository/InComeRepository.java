package com.example.jwt.repository;

import com.example.jwt.custom.InComeCustom;
import com.example.jwt.entity.InCome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

@RepositoryRestResource(path = "income",excerptProjection = InComeCustom.class)
public interface InComeRepository extends JpaRepository<InCome,Integer> {
@Query(value = "select * from in_come where to_card_id_id =(select card.id from card where owner_id=?1)",nativeQuery = true)
    List<InCome> getPersonIncome(Integer owner_id);
}

