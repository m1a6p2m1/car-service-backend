package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "defined_sub_tasks")
public class DefinedSubTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_task_name")
    private String subTaskName;

    @Column(name = "sub_task_price")
    private Double subTaskPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private DefinedTasksEntity definedTasksEntity;

    public DefinedSubTaskEntity() {
    }

    public DefinedSubTaskEntity(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public DefinedTasksEntity getDefinedTasksEntity() {
        return definedTasksEntity;
    }

    public void setDefinedTasksEntity(DefinedTasksEntity definedTasksEntity) {
        this.definedTasksEntity = definedTasksEntity;
    }

    public Double getSubTaskPrice() {
        return subTaskPrice;
    }

    public void setSubTaskPrice(Double subTaskPrice) {
        this.subTaskPrice = subTaskPrice;
    }
}
