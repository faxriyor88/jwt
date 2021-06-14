package com.example.jwt.repository;

import com.example.jwt.entity.Auditing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditingRepository extends JpaRepository<Auditing,Integer> {

    @Query(value = "select * from auditing where user_id=?1",nativeQuery = true)
    Optional<Auditing> getByUser_id(Integer user_id);

    boolean existsByUser_id(Integer user_id);
}
