package com.example.microservicesassestments.repository;

import com.example.commonsdomainassestment.entity.Assestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssestmentRepository extends JpaRepository<Assestment, Long> {

    @Query("SELECT a FROM Assestment a WHERE a.name LIKE %?1%")
    public List<Assestment> findByNombre(String term);
}
