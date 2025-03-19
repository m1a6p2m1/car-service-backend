package com.bit.backend.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubTask> subTasks = new ArrayList<>();

    private Integer progress = 0;

    @Column(name = "current_state")
    private String currentState = "Not Started";

    @Column(name = "assigned_employee")
    private String assignedEmployee;

    // Constructors
    public Task() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public List<SubTask> getSubTasks() { return subTasks; }
    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks.clear();
        if (subTasks != null) {
            subTasks.forEach(this::addSubTask);
        }
    }
    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this.progress = progress; }
    public String getCurrentState() { return currentState; }
    public void setCurrentState(String currentState) { this.currentState = currentState; }
    public String getAssignedEmployee() { return assignedEmployee; }
    public void setAssignedEmployee(String assignedEmployee) { this.assignedEmployee = assignedEmployee; }

    // Helper methods for subTasks
    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        subTask.setTask(this);
    }

    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
        subTask.setTask(null);
    }
}