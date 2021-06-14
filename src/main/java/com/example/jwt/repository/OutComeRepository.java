package com.example.jwt.repository;

import com.example.jwt.custom.OutComeCustom;
import com.example.jwt.entity.OutCome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource (path = "outcome",excerptProjection = OutComeCustom.class)
public interface OutComeRepository extends JpaRepository<OutCome,Integer> {
    @Query(value = "select * from out_come where from_card_id_id=(select card.id from card where owner_id=?1)",nativeQuery = true)
    List<OutCome> getPersonOutCome(Integer owner_id);

}
