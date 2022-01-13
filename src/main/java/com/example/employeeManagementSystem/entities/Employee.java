package com.example.employeeManagementSystem.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String employeeName;

    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    public Employee() {
    }

    public Employee(String employeeName, Date joiningDate) {
        this.employeeName = employeeName;
        this.joiningDate = joiningDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", joiningDate=" + joiningDate +
                '}';
    }
}
