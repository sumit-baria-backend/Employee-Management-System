package com.example.employeeManagementSystem.dto.request;

public class EmployeeDetails {
    private long employeeId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    private String employeeName;

    public EmployeeDetails(String employeeName) {
        this.employeeName = employeeName;
    }

    public EmployeeDetails() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
