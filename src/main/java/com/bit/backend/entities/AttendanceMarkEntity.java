package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_mark",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id", "attendance_date"})
        })
public class AttendanceMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    @Column(name = "attendance_date")
    private LocalDate date;
    @Column(name = "attendance_status", nullable = false)
    private String attendanceStatus;

    public AttendanceMarkEntity() { }

    public AttendanceMarkEntity(long id, EmployeeEntity employee, String attendanceStatus) {
        this.id = id;
        this.employee = employee;
        this.date = LocalDate.now();
        this.attendanceStatus = attendanceStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
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

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
