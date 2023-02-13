package com.javatechstack.datajpa.service;

import com.javatechstack.datajpa.entity.Certification;
import com.javatechstack.datajpa.entity.Employee;
import com.javatechstack.datajpa.repository.CeritificateJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CertificationDbService {

    @Autowired
    CeritificateJpaRepository ceritificateJpaRepository;

    public List<Certification> getAllCertification() {
        return ceritificateJpaRepository.findAll();
    }

}
