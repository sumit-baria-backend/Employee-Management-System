package com.example.employeeManagementSystem.Services;

import com.example.employeeManagementSystem.dao.EmployeeDao;
import com.example.employeeManagementSystem.dto.response.EmployeeDetails;
import com.example.employeeManagementSystem.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    //Inject the object of employeeDao
    @Autowired
    private EmployeeDao employeeDao;


    //Get All Employee service
    @Override
    public ResponseEntity<List<EmployeeDetails>> getEmployees(int page) {

        List<EmployeeDetails> employeeDetailsList = new LinkedList<>();
        Pageable firstPageWithFiveElement = PageRequest.of(page, 5);
        Page<Employee> employeeDetailsList1 =  employeeDao.findAll(firstPageWithFiveElement);

        for(Employee employee: employeeDetailsList1){
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployeeId(employee.getEmployeeId());
            employeeDetails.setEmployeeName(employee.getEmployeeName());
            employeeDetails.setJoiningDate(employee.getJoiningDate());
            employeeDetailsList.add(employeeDetails);
        }

        return ResponseEntity.status(HttpStatus.OK).body(employeeDetailsList);
    }

    //Add employee in DB
    @Override
    public ResponseEntity<EmployeeDetails> setEmployee(com.example.employeeManagementSystem.dto.request.EmployeeDetails employeeDetail) {
        Employee e =  new Employee(employeeDetail.getEmployeeName(),  LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Employee employee = employeeDao.save(e);

        EmployeeDetails employeeDetails = new EmployeeDetails(employee.getEmployeeId(),employee.getEmployeeName(), employee.getJoiningDate());
        return ResponseEntity.status(HttpStatus.OK).body(employeeDetails);
    }

    //Delete Employee Record
    @Override
    public ResponseEntity<String> deleteEmployee(Long employeeId) {
        if(employeeDao.existsById(employeeId)){
            Employee employee = employeeDao.getById(employeeId);
            employeeDao.delete(employee);
            return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        }
    }

    //Get particular employee Record
    @Override
    public ResponseEntity<EmployeeDetails> getEmployee(Long employeeId) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        Employee employee = employeeDao.getById(employeeId);

        employeeDetails.setEmployeeId(employee.getEmployeeId());
        employeeDetails.setEmployeeName(employee.getEmployeeName());
        employeeDetails.setJoiningDate(employee.getJoiningDate());
        return ResponseEntity.status(HttpStatus.OK).body(employeeDetails);
    }

    //Edit the details of Employee
    @Override
    public ResponseEntity<String> editEmployee(com.example.employeeManagementSystem.dto.request.EmployeeDetails employeeDetails) {
        Employee employee;
        if(employeeDao.existsById(employeeDetails.getEmployeeId())){
            employee = employeeDao.getById(employeeDetails.getEmployeeId());
            employee.setEmployeeName(employeeDetails.getEmployeeName());
            employee.setUpdatedAt(LocalDateTime.now());
            employeeDao.save(employee);
            return ResponseEntity.status(HttpStatus.OK).body("Employee Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        }

    }


}
