package com.bit.backend.dtos;

public class AllTaskDto {
    private long allTaskId;
    private String inProgressTasks;
    private String taskName;
    private String taskCreatedBy;
    private String assignedCustomer;
    private String progress;
    private String priority;
    private String viewTaskStatus;
    private String subTasks;

    public AllTaskDto() {
    }

    public AllTaskDto(long allTaskId, String inProgressTasks, String taskName, String taskCreatedBy, String assignedCustomer, String progress, String priority, String viewTaskStatus, String subTasks) {
        this.allTaskId = allTaskId;
        this.inProgressTasks = inProgressTasks;
        this.taskName = taskName;
        this.taskCreatedBy = taskCreatedBy;
        this.assignedCustomer = assignedCustomer;
        this.progress = progress;
        this.priority = priority;
        this.viewTaskStatus = viewTaskStatus;
        this.subTasks = subTasks;
    }

    public long getAllTaskId() {
        return allTaskId;
    }

    public void setAllTaskId(long allTaskId) {
        this.allTaskId = allTaskId;
    }

    public String getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(String inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
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

    public String getAssignedCustomer() {
        return assignedCustomer;
    }

    public void setAssignedCustomer(String assignedCustomer) {
        this.assignedCustomer = assignedCustomer;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String process) {
        this.progress = process;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getViewTaskStatus() {
        return viewTaskStatus;
    }

    public void setViewTaskStatus(String viewTaskStatus) {
        this.viewTaskStatus = viewTaskStatus;
    }

    public String getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(String subTasks) {
        this.subTasks = subTasks;
    }
}
