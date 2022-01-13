package com.example.employeeManagementSystem.Services;

import com.example.employeeManagementSystem.dao.EmployeeDao;
import com.example.employeeManagementSystem.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    //Inject the object of employeeDao
    @Autowired
    private EmployeeDao employeeDao;


    //Get All Employee sevice
    @Override
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    //Add employee in DB
    @Override
    public Employee setEmployee(Employee employee) {
        Employee e =  new Employee(employee.getEmployeeName(), new Date());
        employeeDao.save(e);
        return e;
    }

    //Delete Employee Record
    @Override
    public String deleteEmployee(Long employeeId) {
        if(employeeDao.existsById(employeeId)){
            employeeDao.deleteById(employeeId);
            return "Employee Deleted";
        } else{
            return "Employee Doesn't Exists";
        }
    }

    //Get particular employee Record
    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeDao.findById(employeeId);
    }

    @Override
    public Employee getEmployeeOne(Long employeeId) {
        Employee emp = null;
        if(employeeDao.existsById(employeeId)){
            emp = employeeDao.getById(employeeId);
        }
        return emp;
    }


}
