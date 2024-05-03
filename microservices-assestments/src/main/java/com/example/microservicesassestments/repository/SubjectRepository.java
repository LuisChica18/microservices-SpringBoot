package com.example.microservicesassestments.repository;

import com.example.commonsdomainassestment.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
