package com.bit.backend.dtos;

public class AttendanceMarkEmployeeDto {
    private Long employeeId;
    private String fullName;
    private String status;

    public AttendanceMarkEmployeeDto() {
    }

    public AttendanceMarkEmployeeDto(Long employeeId, String fullName, String status) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
