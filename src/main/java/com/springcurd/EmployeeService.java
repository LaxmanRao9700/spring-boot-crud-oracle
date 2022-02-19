package com.springcurd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeRepository employeRepository;

	public Employee createEmployee(Employee employee) {
		return employeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeRepository.findAll();
	}

	public Employee getEmployee(Integer id) {
		Optional<Employee> employees = employeRepository.findById(id);
		Employee employee = employees.get();
		return employee;
	}
}
