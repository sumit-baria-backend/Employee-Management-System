package com.example.employeeManagementSystem;

import com.example.employeeManagementSystem.Services.EmployeeServiceImpl;
import com.example.employeeManagementSystem.dao.EmployeeDao;
import com.example.employeeManagementSystem.dto.response.EmployeeDetails;
import com.example.employeeManagementSystem.entities.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeManagementSystemApplicationTests {

	@Mock
	private EmployeeDao employeeDao;

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

//Test about the Delete by employee Id Service
	@Test
	public void givenEmployeeId_WhenDeleteRequestisRaise_thanItshouldBeDeletedFromDatabase()
	{
		long employeeResignedId=5;
		ArgumentCaptor<Long> employeeIdCapturer = ArgumentCaptor.forClass(Long.class);
		when(employeeDao.existsById(employeeResignedId)).thenReturn(true);
		employeeServiceImpl.deleteEmployee(employeeResignedId);
		verify(employeeDao,times(1)).getById(employeeIdCapturer.capture());
		assertEquals(employeeResignedId, employeeIdCapturer.getValue());
	}

//	Test about get employee by Id service
	@Test
	public void giveEmployeeId_WhenGetEmployeeById_ThenItSchouldReturnEmployee(){
		long employeeId = 2;
		LocalDate date = LocalDate.of(2021,04,12);
		Employee employee = new Employee("Sumit", date);
		employee.setEmployeeId(employeeId);

		EmployeeDetails employeeDetails1 = new EmployeeDetails();
		employeeDetails1.setEmployeeId(employeeId);
		employeeDetails1.setEmployeeName("Sumit");
		employeeDetails1.setJoiningDate(date);

		ResponseEntity<EmployeeDetails> employeeDetailsResponseEntity = ResponseEntity.status(HttpStatus.OK).body(employeeDetails1);

		when(employeeDao.getById(employeeId)).thenReturn(employee);

		ResponseEntity<EmployeeDetails> actucal = employeeServiceImpl.getEmployee(employeeId);

		assertEquals( employeeDetailsResponseEntity.getStatusCode(),actucal.getStatusCode()	);
		assertEquals(actucal.getBody().getEmployeeId(), employeeDetailsResponseEntity.getBody().getEmployeeId());
		assertEquals(actucal.getBody().getEmployeeName(), employeeDetailsResponseEntity.getBody().getEmployeeName());
		assertEquals(actucal.getBody().getJoiningDate(), employeeDetailsResponseEntity.getBody().getJoiningDate());
	}

//	Test about edit employee by Id service
	@Test
	public void giveEmployeeId_WhenEditEmployee_ThenItShouldEditEmployeeDate(){
		long employeeId = 2;
		com.example.employeeManagementSystem.dto.request.EmployeeDetails employeeDetails = new com.example.employeeManagementSystem.dto.request.EmployeeDetails();
		employeeDetails.setEmployeeId(employeeId);
		employeeDetails.setEmployeeName("Raju");

		LocalDate date = LocalDate.of(2021,04,28);
		LocalDateTime localDate = LocalDateTime.of(2021, 04, 28, 04, 24);
		Employee employee = new Employee("Sumit", date, localDate, localDate);
		employee.setEmployeeId(employeeId);

		ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

		when(employeeDao.existsById(employeeId)).thenReturn(true);
		when(employeeDao.getById(employeeId)).thenReturn(employee);

		ResponseEntity<String> actual = employeeServiceImpl.editEmployee(employeeDetails);
		verify(employeeDao,times(1)).save(employeeArgumentCaptor.capture());

		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body("Employee Updated");

		assertEquals(expected, actual);

	}

	//Test about the adding employee details in Database service
	@Test
	public void givenEmployeeDetails_WhenAddingEmployee_ThenItShouldbeAddedInDatabase(){
		long employeeId = 2;
		Employee employee = new Employee("Sumit", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
		employee.setEmployeeId(employeeId);

		com.example.employeeManagementSystem.dto.request.EmployeeDetails employeeDetails = new com.example.employeeManagementSystem.dto.request.EmployeeDetails();
		employeeDetails.setEmployeeName(employee.getEmployeeName());
		employeeDetails.setEmployeeId(employee.getEmployeeId());

		EmployeeDetails employeeDetails1 = new EmployeeDetails(employee.getEmployeeId(),employee.getEmployeeName(), employee.getJoiningDate());
		when(employeeDao.save(any())).thenReturn(employee);
		ResponseEntity<EmployeeDetails> actualEmployeeDetails = employeeServiceImpl.setEmployee(employeeDetails);

		ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
		verify(employeeDao,times(1)).save(employeeArgumentCaptor.capture());

		ResponseEntity<EmployeeDetails> expactedEmployeeDetails = ResponseEntity.status(HttpStatus.OK).body(employeeDetails1);
		assertEquals(expactedEmployeeDetails.getStatusCodeValue(), actualEmployeeDetails.getStatusCodeValue());
		assertEquals(expactedEmployeeDetails.getBody().getEmployeeId(), actualEmployeeDetails.getBody().getEmployeeId());
		assertEquals(expactedEmployeeDetails.getBody().getEmployeeName(), actualEmployeeDetails.getBody().getEmployeeName());
		assertEquals(expactedEmployeeDetails.getBody().getJoiningDate(), actualEmployeeDetails.getBody().getJoiningDate());
	}

	//Test For Pagination in employee Getting
	@Test
	public void givenNothing_WhenGettingEmployees_ThenGetPageListOfEmployeesForCertainNoSize(){
		int page = 1;
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1,"Sumit",LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));
		employeeList.add(new Employee(2,"Bhumi",LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));
		employeeList.add(new Employee(3,"Het",LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));
		employeeList.add(new Employee(4,"Kaushal",LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));
		employeeList.add(new Employee(5,"Rohan",LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));

		List<EmployeeDetails> employeeDetailsList = new LinkedList<>();

		for(Employee employee: employeeList){
			EmployeeDetails employeeDetails = new EmployeeDetails(employee.getEmployeeId(),employee.getEmployeeName(), employee.getJoiningDate());
			employeeDetailsList.add(employeeDetails);
		}

		Pageable firstFiveEmployees = PageRequest.of(page, 5);
		Page<Employee> employeePage = new PageImpl<>(employeeList);
		when(employeeDao.findAll(firstFiveEmployees)).thenReturn( employeePage);

		ResponseEntity<List<EmployeeDetails>> employeeDetailsResponseEntity = employeeServiceImpl.getEmployees(page);

		ResponseEntity<List<EmployeeDetails>> expactedEmployeeDetails = ResponseEntity.status(HttpStatus.OK).body(employeeDetailsList);

		ArgumentCaptor<EmployeeDetails> employeeArgumentCaptor = ArgumentCaptor.forClass(EmployeeDetails.class);
		verify(employeeDao,times(1)).findAll((Pageable) employeeArgumentCaptor.capture());

		for (int i=0;i<employeeList.size();i++){
			assertEquals(expactedEmployeeDetails.getBody().get(i).getEmployeeId(), employeeDetailsResponseEntity.getBody().get(i).getEmployeeId());
			assertEquals(expactedEmployeeDetails.getBody().get(i).getEmployeeName(), employeeDetailsResponseEntity.getBody().get(i).getEmployeeName());
			assertEquals(expactedEmployeeDetails.getBody().get(i).getJoiningDate(), employeeDetailsResponseEntity.getBody().get(i).getJoiningDate());
		}
	}

}
