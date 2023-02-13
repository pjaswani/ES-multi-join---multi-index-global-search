package com.javatechstack.datajpa.service;

import com.javatechstack.datajpa.entity.Employee;
import com.javatechstack.datajpa.repository.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDBService {
    @Autowired
    EmployeeJpaRepository employeeJpaRepository;



    public Employee addEmployee(Employee employee) {

        return employeeJpaRepository.save(employee);
    }

    public List<Employee> getEmployees() {
     List<Employee> empList = employeeJpaRepository.findAll();
        return empList;
    }


}

