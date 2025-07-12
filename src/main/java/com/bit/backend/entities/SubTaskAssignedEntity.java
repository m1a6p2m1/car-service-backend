package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_tasks_assign")
public class SubTaskAssignedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "assigned_user")
    private Long assignedUserId;

    @Column(name = "unique_sub_task_no")
    private String uniqueSubTaskNo;

    @Column(name = "status")
    private String status;

    @Column(name = "supervisor")
    private Long supervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskAssignEntity taskAssignEntity;

    // Constructors
    public SubTaskAssignedEntity() {}

    public SubTaskAssignedEntity(String description) {
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public TaskAssignEntity getTaskAssignEntity() {
        return taskAssignEntity;
    }

    public void setTaskAssignEntity(TaskAssignEntity taskAssignEntity) {
        this.taskAssignEntity = taskAssignEntity;
    }

    public String getUniqueSubTaskNo() {
        return uniqueSubTaskNo;
    }

    public void setUniqueSubTaskNo(String uniqueSubTaskNo) {
        this.uniqueSubTaskNo = uniqueSubTaskNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }
}