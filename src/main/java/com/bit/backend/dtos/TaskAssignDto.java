package com.bit.backend.dtos;

import java.util.List;

public class TaskAssignDto {
    private Long id;
    private String taskName;
    private String taskCreatedBy;
    private String customerName;
    private String status;
    private List<SubTaskAssignDto> subTasks;
    private Long customerId;

    public TaskAssignDto(Long id, String taskName, String taskCreatedBy,
                         String customerName, String status, List<SubTaskAssignDto> subTasks, Long customerId) {
        this.id = id;
        this.taskName = taskName;
        this.taskCreatedBy = taskCreatedBy;
        this.customerName = customerName;
        this.status = status;
        this.subTasks = subTasks;
        this.customerId = customerId;
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
}
