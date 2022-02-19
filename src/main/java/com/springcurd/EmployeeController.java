package com.springcurd;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/EmployeeAPI")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/testEmployee")
	public ResponseEntity<String> testEmployee() {
		System.out.println("Test Employee Method..");
		return new ResponseEntity<String>("Employee test.....", HttpStatus.OK);
	}

	@PostMapping("/createEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		System.out.println("Create Employee Method..");
		Employee emp = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		System.out.println("Get AllEmployee Method..");
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") Integer id) {
		System.out.println("Get Employee By ID @PathVariable Method..");
		Employee emp = employeeService.getEmployee(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<Employee> getEmployeeById(@PathParam(value = "id") Integer id) {
		System.out.println("Get Employee By ID @PathParam Method..");
		Employee emp = employeeService.getEmployee(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

}
