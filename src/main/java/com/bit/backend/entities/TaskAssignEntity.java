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
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_created_by")
    private String taskCreatedBy;
    @Column(name = "customer_name")
    private String customerName;
    @Column (name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "taskAssignEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubTaskAssignedEntity> subTasks = new ArrayList<>();
    @Column(name = "customer_id")
    private Long customerId;

    public TaskAssignEntity() {
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

    public List<SubTaskAssignedEntity> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskAssignedEntity> subTaskAssignedEntityList) {
        this.subTasks.clear();
        if (subTaskAssignedEntityList != null) {
            subTaskAssignedEntityList.forEach(this::addSubTask);
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
