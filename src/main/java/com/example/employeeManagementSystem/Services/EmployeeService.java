package com.example.employeeManagementSystem.Services;

import com.example.employeeManagementSystem.dto.response.EmployeeDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDetails> getEmployees();

    public ResponseEntity<EmployeeDetails> setEmployee(com.example.employeeManagementSystem.dto.request.EmployeeDetails employee);

    public ResponseEntity<String> deleteEmployee(Long employeeId);

    public EmployeeDetails getEmployee(Long employeeId);


    public ResponseEntity<String> editEmployee(com.example.employeeManagementSystem.dto.request.EmployeeDetails employeeDetails);
}
