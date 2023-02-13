package com.javatechstack.datajpa.controller;

import com.javatechstack.datajpa.service.CertificateElasticService;
import com.javatechstack.datajpa.service.EmployeeElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DNAController {

    @Autowired
    private EmployeeElasticService employeeElasticService;

    @Autowired
    private CertificateElasticService certificateElasticService;

    @GetMapping("/dna/{keyword}")
    public List<Object> getAllIndex(@PathVariable String keyword)
    {
        List<Object> result=new ArrayList<>();
        result.addAll(employeeElasticService.getEmployeesUsingKeyword(keyword));
        result.addAll(certificateElasticService.getCertificatesUsingKeyword(keyword));
        return result;
    }

}
