package com.javatechstack.datajpa.esindex;

import com.javatechstack.datajpa.model.EmployeeCertification;
import com.javatechstack.datajpa.model.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeIndex {

	@Id
	int employeeId;
	String name;
	String designation;
	String email;
	String phoneNo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	LocalDateTime dateOfJoining;
	int prevExperience;

	List<EmployeeSkill> skills;

	List<EmployeeCertification> certification;


	
}
