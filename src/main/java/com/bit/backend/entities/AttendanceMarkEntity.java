package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_mark")
public class AttendanceMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "employee_id")
    private String empId;
    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status")
    private String status;

    public AttendanceMarkEntity() {
        this.dateAndTime = LocalDateTime.now(); // Auto-set current date-time
        this.date = LocalDate.now(); // Auto-set current date
    }

    public AttendanceMarkEntity(long id, String empId, String status) {
        this.id = id;
        this.empId = empId;
        this.dateAndTime = LocalDateTime.now();
        this.date = LocalDate.now();
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
