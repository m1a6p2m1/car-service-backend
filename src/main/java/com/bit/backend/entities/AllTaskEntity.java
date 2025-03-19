package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "all_tasks")
public class AllTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long allTaskId;

    @Column(name = "in_progress_task")
    private String inProgressTasks;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_created_by")
    private String taskCreatedBy;
    @Column(name = "assigned_customer")
    private String assignedCustomer;
    @Column(name = "progress")
    private String progress;
    @Column(name = "priority")
    private String priority;
    @Column(name = "view_task_status")
    private String viewTaskStatus;
    @Column(name = "sub_tasks")
    private String subTasks;

    public AllTaskEntity() {
    }

    public AllTaskEntity(long allTaskId, String inProgressTasks, String taskName, String taskCreatedBy, String assignedCustomer, String progress, String priority, String viewTaskStatus, String subTasks) {
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

    public void setInProgressTasks(String inProgressTask) {
        this.inProgressTasks = inProgressTask;
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

    public void setProgress(String progress) {
        this.progress = progress;
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
