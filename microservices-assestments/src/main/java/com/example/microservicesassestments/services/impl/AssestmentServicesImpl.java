package com.example.microservicesassestments.services.impl;

import com.example.commonsdomainassestment.entity.Assestment;
import com.example.commonsdomainassestment.entity.Subject;
import com.example.commonsmicroservices.service.impl.CommonServicesImpl;
import com.example.microservicesassestments.repository.AssestmentRepository;
import com.example.microservicesassestments.repository.SubjectRepository;
import com.example.microservicesassestments.services.AssestmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssestmentServicesImpl extends CommonServicesImpl<Assestment, AssestmentRepository> implements AssestmentServices {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Assestment> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }
}
