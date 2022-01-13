package com.example.employeeManagementSystem.controller;

import com.example.employeeManagementSystem.Services.EmployeeService;
import com.example.employeeManagementSystem.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Getting all Employee Data
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return this.employeeService.getEmployees();
    }

    //Getting Particular Employee Data
    //Parameter: employeeId: Id of Employee
    @GetMapping("/employee/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable Long employeeId){
        return this.employeeService.getEmployee(employeeId);
    }

    //Getting Particular Employee Data
    //Parameter: employeeId: Id of Employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeOne(@PathVariable Long employeeId){
        return this.employeeService.getEmployeeOne(employeeId);
    }

    //Adding Employee Detail
    //Parameter: employee: Object of Employee
    @PostMapping("/employee")
    public Employee setEmployee(@RequestBody Employee employee){
        return this.employeeService.setEmployee(employee);
    }

    //Removing Employee Record From Database
    //Parameter: employeeId: Id of Employee
    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId){
        return this.employeeService.deleteEmployee(employeeId);
    }


}
