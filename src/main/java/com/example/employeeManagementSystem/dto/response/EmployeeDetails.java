package com.example.employeeManagementSystem.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDetails {
    private long employeeId;
    private String employeeName;
    private LocalDate joiningDate;


    public EmployeeDetails(String employeeName, LocalDate joiningDate) {
        this.employeeName = employeeName;
        this.joiningDate = joiningDate;

    }

    public EmployeeDetails(long employeeId, String employeeName, LocalDate joiningDate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.joiningDate = joiningDate;

    }


    public EmployeeDetails() {
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", joiningDate=" + joiningDate +
                '}';
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
