package com.example.employeeManagementSystem.dao;

import com.example.employeeManagementSystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
