package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "task_assign")
public class TaskAssignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_created_by")
    private String taskCreatedBy;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "taskAssignEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubTaskAssignedEntity> subTasks = new ArrayList<>();

    public TaskAssignEntity() {
    }

    public TaskAssignEntity(Long id, long taskId, String taskName, String taskCreatedBy,
                            String customerName, String status, List<SubTaskAssignedEntity> subTasks) {
        this.id = id;
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskCreatedBy = taskCreatedBy;
        this.customerName = customerName;
        this.status = status;
        this.subTasks = subTasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<SubTaskAssignedEntity> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskAssignedEntity> subTaskAssignedEntityList) {
        this.subTasks.clear();
        if (subTaskAssignedEntityList != null) {
            subTaskAssignedEntityList.forEach(this::addSubTask);
        }
    }

    public void addSubTask(SubTaskAssignedEntity subTaskAssignedEntity) {
        subTasks.add(subTaskAssignedEntity);
        subTaskAssignedEntity.setTaskAssignEntity(this);
    }

    public void removeSubTask(SubTaskAssignedEntity subTaskAssignedEntity) {
        subTasks.remove(subTaskAssignedEntity);
        subTaskAssignedEntity.setTaskAssignEntity(null);
    }
}
