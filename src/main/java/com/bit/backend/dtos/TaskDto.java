package com.bit.backend.dtos;

import com.bit.backend.entities.SubTask;

import java.util.List;

public class TaskDto {
    private Long id;
    private String taskName;
    private String createdBy;
    private String customerName;
    private List<SubTaskDto> subTasks;
    private Integer progress;
    private String currentState;
    private String assignedEmployee;

    public TaskDto(Long id, String taskName, String createdBy, String customerName, List<SubTaskDto> subTasks, Integer progress, String currentState, String assignedEmployee) {
        this.id = id;
        this.taskName = taskName;
        this.createdBy = createdBy;
        this.customerName = customerName;
        this.subTasks = subTasks;
        this.progress = progress;
        this.currentState = currentState;
        this.assignedEmployee = assignedEmployee;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<SubTaskDto> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskDto> subTasks) {
        this.subTasks = subTasks;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
}
