package com.example.employeeManagementSystem;

import com.example.employeeManagementSystem.Services.EmployeeService;
import com.example.employeeManagementSystem.dao.EmployeeDao;
import com.example.employeeManagementSystem.entities.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EmployeeManagementSystemApplicationTests {

	@Mock
	private EmployeeDao employeeDao;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void testCreate(){
		Employee e = new Employee();
		e.setEmployeeName("Rajesh kumar");
		e.setJoiningDate(LocalDate.now());
		Employee a = employeeDao.save(e);
		assertNotNull(a.getEmployeeId());
	}
}
