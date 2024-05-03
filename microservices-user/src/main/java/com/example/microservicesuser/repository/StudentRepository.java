package com.example.microservicesuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.commonsdomain.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.name LIKE %?1% OR s.lastname LIKE %?1%")
    public List<Student> findByNameOrLastname(String term);
}
