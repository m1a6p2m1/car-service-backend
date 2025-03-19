package com.bit.backend.dtos;

public class TaskAssignDto {
    private long taskId;
    private String taskName;
    private String taskCreatedBy;
    private String customerName;
    private String status;

    public TaskAssignDto() {
    }

    public TaskAssignDto(long taskId, String taskName, String taskCreatedBy, String customerName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskCreatedBy = taskCreatedBy;
        this.customerName = customerName;
        this.status = status;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
