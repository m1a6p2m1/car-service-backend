package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_tasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    // Constructors
    public SubTask() {}

    public SubTask(String description) {
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }
}