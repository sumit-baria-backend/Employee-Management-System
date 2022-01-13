package com.example.employeeManagementSystem.Services;

import com.example.employeeManagementSystem.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> getEmployees();

    public Employee setEmployee(Employee employee);

    public String deleteEmployee(Long employeeId);

    public Optional<Employee> getEmployee(Long employeeId);

    public Employee getEmployeeOne(Long employeeId);
}
