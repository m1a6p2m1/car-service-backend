package com.bit.backend.dtos;

import java.util.List;

public class TaskAssignDto {
    private Long id;
    private String taskName;
    private String taskCreatedBy;
    private String customerName;
    private String licencePlate;
    private String vehicleType;
    private String email;
    private String description;
    private String status;
    private String uniqueTaskNo;
    private List<SubTaskAssignDto> subTasks;
    private Long customerId;
    private Long supervisor;

    public TaskAssignDto() {
    }

    public TaskAssignDto(Long id, String taskName, String taskCreatedBy, String customerName, String licencePlate, String vehicleType, String email, String description, String status, String uniqueTaskNo, List<SubTaskAssignDto> subTasks, Long customerId, Long supervisor) {
        this.id = id;
        this.taskName = taskName;
        this.taskCreatedBy = taskCreatedBy;
        this.customerName = customerName;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.email = email;
        this.description = description;
        this.status = status;
        this.uniqueTaskNo = uniqueTaskNo;
        this.subTasks = subTasks;
        this.customerId = customerId;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCreatedBy() {
        return taskCreatedBy;
    }

    public void setTaskCreatedBy(String taskCreatedBy) {
        this.taskCreatedBy = taskCreatedBy;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SubTaskAssignDto> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskAssignDto> subTasks) {
        this.subTasks = subTasks;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueTaskNo() {
        return uniqueTaskNo;
    }

    public void setUniqueTaskNo(String uniqueTaskNo) {
        this.uniqueTaskNo = uniqueTaskNo;
    }

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }
}
