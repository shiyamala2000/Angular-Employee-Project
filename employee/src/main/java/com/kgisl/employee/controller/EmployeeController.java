package com.kgisl.employee.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.kgisl.employee.dto.EmployeeDTO;
import com.kgisl.employee.entity.Employee;
import com.kgisl.employee.entity.TechnicalSkill;
import com.kgisl.employee.service.EmployeeService;

@Controller
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200/*")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@GetMapping("/")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		List<Employee> employees = employeeService.getAll();

		List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> {
			EmployeeDTO dto = new EmployeeDTO();
			dto.setId(employee.getId());
			dto.setEmployeeName(employee.getEmployeeName());
			dto.setMobile(employee.getMobile());
			dto.setGender(employee.getGender());
			dto.setAddress(employee.getAddress());
			dto.setEmployeeCode(employee.getEmployeeCode());
			dto.setDepartment(employee.getDepartment());
			dto.setDesignation(employee.getDesignation());
			dto.setEmail(employee.getEmail());
			dto.setTechnicalSkills(employee.getTechnicalSkills());

			logger.info("List of all DTO: " + dto);
			return dto;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
	}

//	public  ResponseEntity<List<Employee>> getAllEmployee(){
//		 List<Employee> l = employeeService.getAll();
//
//		 for (Employee employee : l) {
//		        System.out.println("Employee: " + employee); 
//		    }
//		 return new ResponseEntity<>(l, HttpStatus.OK);
//	}

	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Integer id) {
		Optional<Employee> l = employeeService.getById(id);
		System.out.println("Get employee " + l);
		return l;
	}

	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@RequestPart("employeeData") Map<String, Map<String, Object>> formData,@RequestPart("file") MultipartFile file) {
		try {

			Map<String, Object> personalInfoData = formData.get("personalInfo");
			Map<String, Object> employeeDetailsData = formData.get("employeeDetails");

			Employee employee = new Employee();

			List<String> technicalSkillNames = (List<String>) personalInfoData.get("technicalSkills");
			List<TechnicalSkill> technicalSkills = technicalSkillNames.stream().map(skillName -> {
				TechnicalSkill technicalSkill = new TechnicalSkill();
				technicalSkill.setSkill(skillName);
				technicalSkill.setEmployee(employee);
				return technicalSkill;
			}).collect(Collectors.toList());

			employee.setEmployeeName((String) personalInfoData.get("employeeName"));
			employee.setMobile((String) personalInfoData.get("mobile"));
			employee.setGender((String) personalInfoData.get("gender"));
//        employee.setTechnicalSkill((String) personalInfoData.get("technicalSkill"));
			employee.setTechnicalSkills(technicalSkills);

			employee.setAddress((String) personalInfoData.get("address"));

			employee.setEmployeeCode((String) employeeDetailsData.get("employeeCode"));
			employee.setDepartment((String) employeeDetailsData.get("department"));
			employee.setDesignation((String) employeeDetailsData.get("designation"));
			employee.setEmail((String) employeeDetailsData.get("email"));
			System.out.println("Employee request:" + employee);

			if (file != null) {
				byte[] fileData = file.getBytes();
				employee.setEmployeeFile(fileData);
			}

			Employee l = employeeService.insert(employee);
			System.out.println("EmployeeService list:" + l);

			return new ResponseEntity<>(l, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PostMapping("/")
//	public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Map<String, Object>> formData) {
//		Map<String, Object> personalInfoData = formData.get("personalInfo");
//        Map<String, Object> employeeDetailsData = formData.get("employeeDetails");
//        
//        Employee employee = new Employee();
//
//        List<String> technicalSkillNames = (List<String>) personalInfoData.get("technicalSkills");
//        List<TechnicalSkill> technicalSkills = technicalSkillNames.stream()
//                .map(skillName -> {
//                    TechnicalSkill technicalSkill = new TechnicalSkill();
//                    technicalSkill.setSkill(skillName);
//                    return technicalSkill;
//                })
//                .collect(Collectors.toList());
//        
//        employee.setEmployeeName((String) personalInfoData.get("employeeName"));
//        employee.setMobile((String) personalInfoData.get("mobile"));
//        employee.setGender((String) personalInfoData.get("gender"));
//        employee.setTechnicalSkills(technicalSkills);
//       
//        employee.setAddress((String) personalInfoData.get("address"));
//        
//        employee.setEmployeeCode((String) employeeDetailsData.get("employeeCode"));
//        employee.setDepartment((String) employeeDetailsData.get("department"));
//        employee.setDesignation((String) employeeDetailsData.get("designation"));
//        employee.setEmail((String) employeeDetailsData.get("email"));
//        System.out.println("Employee request:"+employee);
//        
//		Employee l = employeeService.insert(employee);
//        System.out.println("EmployeeService list:"+l);
//
//		 return new ResponseEntity<>(l, HttpStatus.CREATED);
//	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee e, @PathVariable int id) {
		Employee l = employeeService.update(e, id);
		System.out.println("update id " + l.getId() + "update name " + l.getEmployeeName());
		return l;
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeService.delete(id);

	}

}
