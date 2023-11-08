package com.kgisl.employee.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.employee.entity.Employee;
import com.kgisl.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
		
	public List<Employee> getAll() {
		List<Employee> employees = employeeRepository.findAll();
		
        logger.info("List of all employees: " + employees);

        return employees;
//		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getById(Integer id) {
		return employeeRepository.findById(id);
//				orElseThrow(()->new NotFoundException("Employee not found"));
	}
	
	public Employee insert(Employee e) {
		return employeeRepository.save(e);
	}

	public Employee update(Employee e,Integer id) {
		return employeeRepository.save(e);
	}

	
	public void delete(Integer id) {
		employeeRepository.deleteById(id);
	}
}
