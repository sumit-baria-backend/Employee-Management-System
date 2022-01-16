package com.example.employeeManagementSystem.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String employeeName;

//    @Temporal(TemporalType.DATE)
    private LocalDate joiningDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", joiningDate=" + joiningDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Employee() {
    }

    public Employee(String employeeName, LocalDate joiningDate) {
        this.employeeName = employeeName;
        this.joiningDate = joiningDate;
    }

    public Employee(String employeeName, LocalDate joiningDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.employeeName = employeeName;
        this.joiningDate = joiningDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
