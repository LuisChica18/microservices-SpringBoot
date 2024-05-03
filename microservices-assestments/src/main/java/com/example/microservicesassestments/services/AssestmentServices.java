package com.example.microservicesassestments.services;

import com.example.commonsdomainassestment.entity.Assestment;
import com.example.commonsdomainassestment.entity.Subject;
import com.example.commonsmicroservices.service.CommonServices;

import java.util.List;

public interface AssestmentServices extends CommonServices<Assestment> {

    public List<Assestment> findByNombre(String term);

    public List<Subject> findAllSubjects();
}
