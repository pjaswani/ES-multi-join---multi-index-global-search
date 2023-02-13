package com.javatechstack.datajpa.controller;

import com.javatechstack.datajpa.builder.EmployeeBuilder;
import com.javatechstack.datajpa.entity.Employee;
import com.javatechstack.datajpa.esindex.EmployeeIndex;
import com.javatechstack.datajpa.service.CertificateElasticService;
import com.javatechstack.datajpa.service.EmployeeDBService;
import com.javatechstack.datajpa.service.EmployeeElasticService;
import jakarta.json.Json;


import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {


    private RestTemplate restTemplate=new RestTemplate();
    @Autowired
    private EmployeeDBService employeeDBService;

    @Autowired
    private EmployeeElasticService employeeElasticService;

    @Autowired
    private CertificateElasticService certificateElasticService;

    @Autowired
    private EmployeeBuilder builder;

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {

        return employeeDBService.addEmployee(employee);
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        List<Employee> emplist=employeeDBService.getEmployees();
        return emplist;
    }

    @PostMapping("/employee/elastic")
    public List<EmployeeIndex> loadEmployeeToElastic() {
        List<Employee> emplist=employeeDBService.getEmployees();
        List<EmployeeIndex> empEsList = new ArrayList<>();
        for (Employee employee : emplist) {
            EmployeeIndex emp=employeeElasticService.getEmployee(employee.getId());

            if(emp==null){
                EmployeeIndex empElastic=builder.getEmployeeDocFromEmpRec(employee);
                employeeElasticService.addEmployeeDocument(empElastic);
                empEsList.add(empElastic);
            }

        }
        return empEsList;
    }

    @GetMapping("/employee/experience/{exp}")
    public List<EmployeeIndex> getEmployeesByExperience(@PathVariable int exp){
        return employeeElasticService.getEmployeesByExperience(exp);
    }

    @GetMapping("/employee/skills/{skill}")
    public List<EmployeeIndex> getEmployeesByExperience(@PathVariable String skill){
        return employeeElasticService.getEmployeesBySkills(skill);
    }

    @GetMapping("/employee/experience/{experience}/skills/{skill}")
    public List<EmployeeIndex> getEmployeesBySkillsAndExp(@PathVariable int experience, @PathVariable String skill){
        return employeeElasticService.getEmployeesBySkillsAndExp(experience,skill);
    }

    @GetMapping("/employee/certification/{certification}")
    public List<EmployeeIndex> getEmployeesBySkillsAndExp(@PathVariable String certification){
        return employeeElasticService.getCertifiedEmployee(certification);
    }

    @GetMapping("/employee/search/keyword/{keyword}")
    public List<EmployeeIndex> getEmployeeUsingKeyword(@PathVariable String keyword){
        return employeeElasticService.getEmployeesUsingKeyword(keyword);
    }


    @GetMapping("/employee/globalsearch/{keyword}")
    public Object getEmployee(@PathVariable String keyword) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jbo="{\"query\": {\"query_string\": {\"query\": \"*"+keyword+"*\"}}}";
        HttpEntity entity = new HttpEntity(jbo, headers);
        System.out.println("Global entity "+entity.getBody());
        return restTemplate.exchange("http://localhost:9200/*/_search",  HttpMethod.POST,entity,Object.class).getBody();

    }



//    @GetMapping("/employee/globalsearchrepo/{keyword}")
//    public Object getEmployeeWithRepo(@PathVariable String keyword) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        //headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        //headers.add("Content-Type",MediaType.APPLICATION_JSON.toString());
////        JSONObject jbo=new JSONObject("{\"query\": {\"query_string\": {\"query\": \"*"+keyword+"*\"}}}");
//        String jbo="{\"query\": {\"query_string\": {\"query\": \"*"+keyword+"*\"}}}";
//        HttpEntity entity = new HttpEntity(jbo, headers);
//        System.out.println("Global entity "+entity.getBody());
//        return restTemplate.getForEntity
//                ("http://localhost:9200/*/_search",Object.class, entity).getBody();
//
//    }
}
