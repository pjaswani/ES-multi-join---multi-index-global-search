package com.javatechstack.datajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String designation;

    @Column
    String email;

    @Column
    String phoneNo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    LocalDateTime dateOfJoining;

    @Column
    int prevExperience;

    @OneToMany(targetEntity = EmployeeSkillDetails.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="emp_fk",referencedColumnName = "id")
    List<EmployeeSkillDetails> skills;

    @OneToMany(targetEntity = EmployeeCertificationDetails.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="emp_fk",referencedColumnName = "id")
    List<EmployeeCertificationDetails> certification;

}
