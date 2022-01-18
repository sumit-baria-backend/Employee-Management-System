package com.example.employeeManagementSystem.controller;

import com.example.employeeManagementSystem.Services.EmployeeService;
import com.example.employeeManagementSystem.dto.response.EmployeeDetails;
import com.example.employeeManagementSystem.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Getting all Employee Data
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDetails>> getEmployees(@RequestParam(value = "page", required = false) int page){
        return this.employeeService.getEmployees(page);
    }

    //Getting Particular Employee Data
    //Parameter: employeeId: Id of Employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeDetails> getEmployee(@PathVariable Long employeeId){
        return this.employeeService.getEmployee(employeeId);
    }

    //Adding Employee Detail
    //Parameter: employee: Object of Employee
    @PostMapping("/employee")
    public ResponseEntity<EmployeeDetails> setEmployee(@RequestBody com.example.employeeManagementSystem.dto.request.EmployeeDetails employee){
        return this.employeeService.setEmployee(employee);
    }

    //Removing Employee Record From Database
    //Parameter: employeeId: Id of Employee
    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
        return this.employeeService.deleteEmployee(employeeId);
    }

    //Updating Employee Name in Database
    //Parameter: employeeId: Id of Employee
    @PutMapping("/employee")
    public ResponseEntity<String> editEmployee(@RequestBody com.example.employeeManagementSystem.dto.request.EmployeeDetails employeeDetails){
        return this.employeeService.editEmployee(employeeDetails);
    }


}
