package com.bit.backend.dtos;

import com.bit.backend.entities.AttendanceMarkEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceMarkDto {
    private long id;
    private LocalDateTime dateAndTime;
    private LocalDate date;
    private String empId;
    private String status;

    public AttendanceMarkDto() {
        this.dateAndTime = LocalDateTime.now(); // Auto-set current timestamp
        this.date = LocalDate.now(); // Auto-set current date
    }

    public AttendanceMarkDto(long id, LocalDateTime dateAndTime, LocalDate date, String empId, String status) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.date = date;
        this.empId = empId;
        this.status = status;
    }

    // Constructor to convert Entity -> DTO
    public AttendanceMarkDto(AttendanceMarkEntity entity) {
        this.id = entity.getId();
        this.dateAndTime = entity.getDateAndTime();
        this.date = entity.getDate();
        this.empId = entity.getEmpId();
        this.status = entity.getStatus();
    }

    // Method to convert DTO -> Entity
    public AttendanceMarkEntity toEntity() {
        AttendanceMarkEntity entity = new AttendanceMarkEntity();
        entity.setId(this.id);
        entity.setDateAndTime(this.dateAndTime);
        entity.setDate(this.date);
        entity.setEmpId(this.empId);
        entity.setStatus(this.status);
        return entity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
