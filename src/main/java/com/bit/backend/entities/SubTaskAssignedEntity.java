package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_tasks")
public class SubTaskAssignedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

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

    public TaskAssignEntity getTaskAssignEntity() {
        return taskAssignEntity;
    }

    public void setTaskAssignEntity(TaskAssignEntity taskAssignEntity) {
        this.taskAssignEntity = taskAssignEntity;
    }
}