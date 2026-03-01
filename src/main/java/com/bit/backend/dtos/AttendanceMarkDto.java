package com.bit.backend.dtos;

import java.time.LocalDate;

public class AttendanceMarkDto {
    private long employeeId;
    private LocalDate date;
    private String attendanceStatus;

    public AttendanceMarkDto() {
//        this.dateAndTime = LocalDateTime.now(); // Auto-set current timestamp
        this.date = LocalDate.now(); // Auto-set current date
    }

    public AttendanceMarkDto(long employeeId, LocalDate date, String attendanceStatus) {
        this.employeeId = employeeId;
        this.date = date;
        this.attendanceStatus = attendanceStatus;
    }

//    // Constructor to convert Entity -> DTO
//    public AttendanceMarkDto(AttendanceMarkEntity entity) {
//        this.id = entity.getId();
//        this.date = entity.getDate();
//        this.employeeId = entity.getEmpId();
//        this.empName = entity.getEmpName();
//        this.status = entity.getStatus();
//    }
//
//    // Method to convert DTO -> Entity
//    public AttendanceMarkEntity toEntity() {
//        AttendanceMarkEntity entity = new AttendanceMarkEntity();
//        entity.setId(this.id);
//        entity.setDate(this.date);
//        entity.setEmpId(this.employeeId);
//        entity.setEmpName(this.empName);
//        entity.setStatus(this.status);
//        return entity;
//    }


    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String status) {
        this.attendanceStatus = status;
    }
}
