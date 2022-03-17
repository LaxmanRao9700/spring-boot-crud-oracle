package com.springcurd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

@EnableJpaRepositories
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { TestBeanConfig.class })
class SpringcurdApplicationTests {

	@Autowired
	private MockMvc mvc;

	private AbstarctTest abstarctTest = new AbstarctTest() {
	};

	@Autowired
	private EmployeeService employeeService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testEmployeeTest() {
		String uri = "/EmployeeAPI/testEmployee";
		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().is(200)).andReturn();
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getAllEmployeesTest() {
		String uri = "/EmployeeAPI/getAllEmployees";
		try {
			Employee employee = employeeObj();
			Employee employee1 = employeeObj1();

			employeeService.createEmployee(employee);
			employeeService.createEmployee(employee1);
			List<Employee> preList = new ArrayList<>();
			preList.add(employee1);
			preList.add(employee);

			String inputJson = abstarctTest.mapToJson(preList);
			try {
				MvcResult mvcResult = mvc.perform(
						MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
						.andExpect(status().is(200)).andReturn();
				System.out.println(mvcResult);
				int status = mvcResult.getResponse().getStatus();
				assertEquals(200, status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getAllEmployeesTestNegitive() {
		String uri = "/EmployeeAPI/getAllEmployees";
		MvcResult mvcResult;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().is(200)).andReturn();
			System.out.println(mvcResult);
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * @Test public void getAllEmployeesTestPositive() {
	 * 
	 * Employee employee= employeeObj(); Employee employee1= employeeObj();
	 * List<Employee> preList= new ArrayList<>(); preList.add(employee1);
	 * preList.add(employee);
	 * 
	 * employeeService.createEmployee(employee);
	 * employeeService.createEmployee(employee1);
	 * 
	 * 
	 * 
	 * List<Employee> list= employeeService.getAllEmployees();
	 * assertEquals(preList.size(), list.size());
	 * 
	 * for(Employee e:list) { assertEquals(employee.getFirstName(),
	 * e.getFirstName()); }
	 * 
	 * 
	 * }
	 */
	public static Employee employeeObj() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setFirstName("Vedansh");
		employee.setLastName("Doredla");
		employee.setEmailId("vedansh@gmail.com");

		return employee;
	}

	public static Employee employeeObj1() {
		Employee employee = new Employee();

		employee.setId(102);
		employee.setFirstName("Laxman");
		employee.setLastName("Doredla");
		employee.setEmailId("laxman@gmail.com");
		return employee;
	}

	public static List<Employee> listEmployee() {
		Employee employee = new Employee();
		Employee employee1 = new Employee();

		employee.setId(101);
		employee.setFirstName("Vedansh");
		employee.setLastName("Doredla");
		employee.setEmailId("vedansh@gmail.com");

		employee1.setId(102);
		employee1.setFirstName("Laxman");
		employee1.setLastName("Doredla");
		employee1.setEmailId("laxman@gmail.com");

		List<Employee> list = new ArrayList<>();
		list.add(employee);
		list.add(employee1);
		return list;
	}
}
