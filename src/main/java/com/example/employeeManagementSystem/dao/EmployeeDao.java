package com.example.employeeManagementSystem.dao;

import com.example.employeeManagementSystem.dto.response.EmployeeDetails;
import com.example.employeeManagementSystem.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface EmployeeDao extends JpaRepository<Employee, Long> {
//    public Page<Employee> findEmployeeById(Pageable pageable);
}
