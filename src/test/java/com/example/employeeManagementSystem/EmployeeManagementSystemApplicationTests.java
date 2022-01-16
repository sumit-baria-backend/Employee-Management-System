package com.example.employeeManagementSystem;

import com.example.employeeManagementSystem.dao.EmployeeDao;
import com.example.employeeManagementSystem.entities.Employee;
import org.hibernate.type.LocalDateTimeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeManagementSystemApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void testCreate(){
		Employee e = new Employee();
		e.setEmployeeName("Rajesh kumar");
		e.setJoiningDate(LocalDate.now());
		Employee a = employeeDao.save(e);
		assertNotNull(a.getEmployeeId());
	}
}
