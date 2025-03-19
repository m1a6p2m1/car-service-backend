package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "task_assign")
public class TaskAssignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_created_by")
    private String taskCreatedBy;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "status")
    private String status;

    public TaskAssignEntity() {
    }

    public TaskAssignEntity(long taskId, String taskName, String taskCreatedBy, String customerName, String status) {
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
